package ch.ebu.pipe_android.beans;

public interface CollectResponseListener {

    void successCollect();

    void errorCollect(Exception e);
}
