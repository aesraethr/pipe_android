package ch.ebu.pipe_android;

import android.app.Application;
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
import ch.ebu.pipe_android.beans.Config;
import ch.ebu.pipe_android.beans.ConfigResponseListener;
import ch.ebu.pipe_android.beans.Payload;

public class PipeCollect extends Application {

    private static PipeCollect instance;
    private static String TAG = PipeCollect.class.getName();
    private static String PREFS = "PipePrefs";
    private WebServices webServices;
    private Payload payload;

    public PipeCollect() {
        instance = this;
    }

    public static Context getContext() {
        return instance;
    }

    @Override
    public void onCreate() {
        webServices = WebServices.getInstance();

        super.onCreate();
    }

    public void setConfiguration(String siteKey, String deviceId){
        setConfiguration(siteKey, null, deviceId);
    }

    public void setConfiguration(String siteKey) {
        // no user Id
        setConfiguration(siteKey, null , Settings.Secure.getString(getContext().getContentResolver(), Settings.Secure.ANDROID_ID));
    }

    public void setConfiguration(String siteKey, final String userId, final String deviceId) {
        final SharedPreferences settings = getSharedPreferences(PREFS ,MODE_PRIVATE);
        final SharedPreferences.Editor editor = settings.edit();

        // Set the configuration
        webServices.setConfiguration(new ConfigResponseListener() {
            @Override
            public void SaveConfig(Config config) {
                payload = new Payload();
                // if cookie id is already set, do not generate a new one
                if (settings.contains("cookie_id") ) {
                    payload.setCookieId(settings.getString("cookie_id","never happens"));
                } else {
                    payload.setCookieId(UUID.randomUUID().toString());
                    editor.putString("cookie_id", payload.getCookieId());
                }
                // generate a new session id each time the configuration is set
                payload.setSessionCookieID(UUID.randomUUID().toString());
                // app type = android for now
                payload.setAppType("android");
                if(userId != null) payload.setUserID(userId);
                payload.setDeviceId(deviceId);

                Browser browserData = new Browser();
                browserData.setJavaEnabled(true);
                browserData.setHostName(getApplicationContext().getPackageName());
                browserData.setLanguage(getResources().getConfiguration().locale.getLanguage());
                browserData.setCharSet(Charset.defaultCharset().displayName());
                // todo set location browserData.setLocation();
                browserData.setTimeZone(TimeZone.getDefault().getRawOffset());
                WindowManager manager = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
                Display display = manager.getDefaultDisplay();
                browserData.setScreenSize(display.getHeight()+"x"+display.getWidth());

                payload.setBrowserData(browserData);

            }

            @Override
            public void ErrorConfig(Exception e) {
                Log.e(TAG, e.getMessage(), e);
            }
        });
    }



}
