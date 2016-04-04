package ch.ebu.pipe_android;

import android.content.Context;
import android.content.SharedPreferences;
import android.provider.Settings;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;

import java.nio.charset.Charset;
import java.util.TimeZone;
import java.util.UUID;

import ch.ebu.pipe_android.beans.Browser;
import ch.ebu.pipe_android.beans.CollectResponseListener;
import ch.ebu.pipe_android.beans.Config;
import ch.ebu.pipe_android.beans.ConfigResponseListener;
import ch.ebu.pipe_android.beans.Payload;

public class PipeCollect {

    private static PipeCollect instance = new PipeCollect();
    private static String TAG = PipeCollect.class.getName();
    private static String PREFS = "PipePrefs";
    private static Context context;
    private WebServices webServices;
    private Payload payload;
    private String siteKey;
    private Config config;
    private String origin;

    public PipeCollect() {
        webServices = WebServices.getInstance();
    }

    public static PipeCollect getInstance() {
        return instance;
    }

    public static Context getContext() {
        return context;
    }

    public void setConfiguration(String siteKey, String deviceId, String origin, Context context){
        setConfiguration(siteKey, null, deviceId, origin, context);
    }

    public void setConfiguration(String siteKey, String origin, Context context) {
        // no user Id
        setConfiguration(siteKey, null, Settings.Secure.getString(getContext().getContentResolver(), Settings.Secure.ANDROID_ID), origin, context);
    }

    public void setConfiguration(final String siteKey, final String userId, final String deviceId, final String origin, final Context context) {
        final SharedPreferences settings = context.getSharedPreferences(PREFS, Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = settings.edit();

        // Set the configuration
        PipeCollect.context = context;
        webServices.setConfiguration(new ConfigResponseListener() {
            @Override
            public void saveConfig(Config config) {
                payload = new Payload();
                // if cookie id is already set, do not generate a new one
                if (settings.contains("cookie_id")) {
                    payload.setCookieId(settings.getString("cookie_id", "never happens"));
                } else {
                    payload.setCookieId(UUID.randomUUID().toString());
                    editor.putString("cookie_id", payload.getCookieId());
                    editor.apply();
                }
                // generate a new session id each time the configuration is set
                payload.setSessionCookieID(UUID.randomUUID().toString());
                // app type = android for now
                payload.setAppType("android");
                if (userId != null) payload.setUserID(userId);
                payload.setDeviceId(deviceId);

                Browser browserData = new Browser();
                browserData.setJavaEnabled(true);
                browserData.setHostName(context.getApplicationContext().getPackageName());
                browserData.setLanguage(context.getResources().getConfiguration().locale.getLanguage());
                browserData.setCharSet(Charset.defaultCharset().displayName());
                // todo set location browserData.setLocation();
                browserData.setTimeZone(TimeZone.getDefault().getRawOffset());
                WindowManager manager = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
                Display display = manager.getDefaultDisplay();
                browserData.setScreenSize(display.getHeight() + "x" + display.getWidth());

                payload.setBrowserData(browserData);

                setSiteKey(siteKey);
                setConfig(config);
                setOrigin(origin);

            }

            @Override
            public void errorConfig(Exception e) {
                Log.e(TAG, e.getMessage(), e);
            }
        }, context );
    }

    public void collectData(String eventAction, Object actionData) {

        if(context == null) {
            // todo : we should find a better way to do this
            Log.e(TAG,"Configuration not set, please call setConfiguration first");

        } else {
            payload.setActionData(actionData);

            webServices.collectData(payload, siteKey, eventAction, origin, config, new CollectResponseListener() {
                @Override
                public void successCollect() {
                    Log.i(TAG, "Payload : " + payload.toString() + " Collected Successfully");
                }

                @Override
                public void errorCollect(Exception e) {
                    Log.e(TAG, e.getMessage(), e);
                }
            });
        }
    }

    private void setSiteKey(String siteKey) {
        this.siteKey = siteKey;
    }

    private void setConfig(Config config) {
        this.config = config;
    }

    private void setOrigin(String origin) {
        this.origin = origin;
    }
}
