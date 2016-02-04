package ch.ebu.pipe_android.beans;

public interface ConfigResponseListener {

    void SaveConfig(Config config);

    void ErrorConfig(Exception e);

}
