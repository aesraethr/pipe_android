package ch.ebu.pipe_android;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import ch.ebu.pipe_android.beans.CollectResponseListener;
import ch.ebu.pipe_android.beans.Config;
import ch.ebu.pipe_android.beans.ConfigResponseListener;
import ch.ebu.pipe_android.beans.Payload;

public class WebServices {

    private RequestQueue requestQueue;

    private static WebServices webServices = new WebServices();

    private String url;
    private String urlSeparator = "/";

    private Config config;


    private WebServices() {
    }

    static WebServices getInstance() {
        return webServices;
    }

    void setConfiguration(final ConfigResponseListener listener, Context context) {


        requestQueue = Volley.newRequestQueue(context);

        url = context.getString(R.string.base_url) + urlSeparator + context.getString(R.string.version) + urlSeparator + context.getString(R.string.url_config);

        final JsonObjectRequest request = new JsonObjectRequest(
                url,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        config = new Gson().fromJson(response.toString(),Config.class);
                        listener.saveConfig(config);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        listener.errorConfig(error);
                    }
                });


        requestQueue.add(request);
    }

    void collectData(final Payload payload, String sitekey,  String eventAction, final String origin, final Config config, final CollectResponseListener listener) {
        // todo : how to chose the right endpoint & protocol ?
        url = "http://" + config.getEndpoints().get(0) + urlSeparator +
                PipeCollect.getContext().getString(R.string.version) + urlSeparator +
                PipeCollect.getContext().getString(R.string.url_collect) + "?" +
                "s=" + sitekey + "&" +
                "e=" + eventAction ;

        final StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                listener.successCollect();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                listener.errorCollect(error);
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String,String> params =  new HashMap<>();
                params.put("User-Agent", System.getProperty("http.agent"));
                params.put("Origin", origin);
                return params;
            }

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("payload", new Gson().toJson(payload));
                return params;
            }
        };

        requestQueue.add(request);
    }



}
