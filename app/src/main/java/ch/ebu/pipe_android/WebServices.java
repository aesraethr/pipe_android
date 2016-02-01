package ch.ebu.pipe_android;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONObject;

import ch.ebu.pipe_android.beans.Config;

public class WebServices {

    private RequestQueue requestQueue;

    private static WebServices webServices;

    private String url;
    private String urlSeparator = "/";


    public WebServices() {
        requestQueue = Volley.newRequestQueue(PipeCollect.getContext());
    }

    public static WebServices getInstance() {
        return webServices;
    }

    public void setConfiguration() {

        url = R.string.base_url + urlSeparator + R.string.version + urlSeparator + R.string.url_config;

        final JsonObjectRequest request = new JsonObjectRequest(
                url,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Config config = new Gson().fromJson(response.toString(),Config.class);
                        Log.e("CONFIG",config.toString());
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Config ERROR",error.getMessage());
                    }
                });


        requestQueue.add(request);
    }

}
