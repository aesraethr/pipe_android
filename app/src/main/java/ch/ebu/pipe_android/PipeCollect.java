package ch.ebu.pipe_android;

import android.app.Application;
import android.content.Context;

public class PipeCollect extends Application {

    private static PipeCollect instance;
    private WebServices webServices;

    private PipeCollect() {
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

}
