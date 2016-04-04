package ch.ebu.pipe_android.beans;

public interface ConfigResponseListener {

    void saveConfig(Config config);

    void errorConfig(Exception e);

}
