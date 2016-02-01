package ch.ebu.pipe_android.beans;

import java.util.List;

public class Config {
    private int maxPostSize;
    private List<String> flags;
    private List<String> endpoints;
    private boolean enabled;
    private String version;

    public Config(int maxPostSize, List<String> flags, List<String> endpoints, boolean enabled, String version) {
        this.maxPostSize = maxPostSize;
        this.flags = flags;
        this.endpoints = endpoints;
        this.enabled = enabled;
        this.version = version;
    }

    public Config() {
    }

    public int getMaxPostSize() {
        return maxPostSize;
    }

    public void setMaxPostSize(int maxPostSize) {
        this.maxPostSize = maxPostSize;
    }

    public List<String> getFlags() {
        return flags;
    }

    public void setFlags(List<String> flags) {
        this.flags = flags;
    }

    public List<String> getEndpoints() {
        return endpoints;
    }

    public void setEndpoints(List<String> endpoints) {
        this.endpoints = endpoints;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "Config{" +
                "maxPostSize=" + maxPostSize +
                ", flags=" + flags +
                ", endpoints=" + endpoints +
                ", enabled=" + enabled +
                ", version='" + version + '\'' +
                '}';
    }
}
