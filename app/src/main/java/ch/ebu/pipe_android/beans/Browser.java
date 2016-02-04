package ch.ebu.pipe_android.beans;

public class Browser {
    private boolean javaEnabled;
    private String hostName;
    private String screenColors;
    private String language;
    private String referer;
    private String charSet;
    private String location;
    private String flashVersion;
    private String pageTitle;
    private String viewPortSize;
    private int timeZone;
    private String page;
    private String screenSize;

    public Browser(boolean javaEnabled, String hostName, String screenColors, String language, String referer, String charSet, String location, String flashVersion, String pageTitle, String viewPortSize, int timeZone, String page, String screenSize) {
        this.javaEnabled = javaEnabled;
        this.hostName = hostName;
        this.screenColors = screenColors;
        this.language = language;
        this.referer = referer;
        this.charSet = charSet;
        this.location = location;
        this.flashVersion = flashVersion;
        this.pageTitle = pageTitle;
        this.viewPortSize = viewPortSize;
        this.timeZone = timeZone;
        this.page = page;
        this.screenSize = screenSize;
    }

    public Browser() {
    }

    public boolean isJavaEnabled() {
        return javaEnabled;
    }

    public void setJavaEnabled(boolean javaEnabled) {
        this.javaEnabled = javaEnabled;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public String getScreenColors() {
        return screenColors;
    }

    public void setScreenColors(String screenColors) {
        this.screenColors = screenColors;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getReferer() {
        return referer;
    }

    public void setReferer(String referer) {
        this.referer = referer;
    }

    public String getCharSet() {
        return charSet;
    }

    public void setCharSet(String charSet) {
        this.charSet = charSet;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getFlashVersion() {
        return flashVersion;
    }

    public void setFlashVersion(String flashVersion) {
        this.flashVersion = flashVersion;
    }

    public String getPageTitle() {
        return pageTitle;
    }

    public void setPageTitle(String pageTitle) {
        this.pageTitle = pageTitle;
    }

    public String getViewPortSize() {
        return viewPortSize;
    }

    public void setViewPortSize(String viewPortSize) {
        this.viewPortSize = viewPortSize;
    }

    public int getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(int timeZone) {
        this.timeZone = timeZone;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getScreenSize() {
        return screenSize;
    }

    public void setScreenSize(String screenSize) {
        this.screenSize = screenSize;
    }

    @Override
    public String toString() {
        return "Browser{" +
                "javaEnabled=" + javaEnabled +
                ", hostName='" + hostName + '\'' +
                ", screenColors='" + screenColors + '\'' +
                ", language='" + language + '\'' +
                ", referer='" + referer + '\'' +
                ", charSet='" + charSet + '\'' +
                ", location='" + location + '\'' +
                ", flashVersion='" + flashVersion + '\'' +
                ", pageTitle='" + pageTitle + '\'' +
                ", viewPortSize='" + viewPortSize + '\'' +
                ", timeZone=" + timeZone +
                ", page='" + page + '\'' +
                ", screenSize='" + screenSize + '\'' +
                '}';
    }
}
