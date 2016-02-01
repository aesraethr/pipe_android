package ch.ebu.pipe_android.beans;

public class Payload {
    Object actionData;
    Browser browserData;
    String sendStart;
    String contentID;
    String userID;
    String sessionCookieID;
    String appType;
    int sessionStart;
    int pageStart;
    String cookieId;
    String deviceId;

    public Payload(Object actionData, Browser browserData, String sendStart, String contentID, String userID, String sessionCookieID, String appType, int sessionStart, int pageStart, String cookieId, String deviceId) {
        this.actionData = actionData;
        this.browserData = browserData;
        this.sendStart = sendStart;
        this.contentID = contentID;
        this.userID = userID;
        this.sessionCookieID = sessionCookieID;
        this.appType = appType;
        this.sessionStart = sessionStart;
        this.pageStart = pageStart;
        this.cookieId = cookieId;
        this.deviceId = deviceId;
    }

    public Payload() {
    }

    public Object getActionData() {
        return actionData;
    }

    public void setActionData(Object actionData) {
        this.actionData = actionData;
    }

    public Browser getBrowserData() {
        return browserData;
    }

    public void setBrowserData(Browser browserData) {
        this.browserData = browserData;
    }

    public String getSendStart() {
        return sendStart;
    }

    public void setSendStart(String sendStart) {
        this.sendStart = sendStart;
    }

    public String getContentID() {
        return contentID;
    }

    public void setContentID(String contentID) {
        this.contentID = contentID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getSessionCookieID() {
        return sessionCookieID;
    }

    public void setSessionCookieID(String sessionCookieID) {
        this.sessionCookieID = sessionCookieID;
    }

    public String getAppType() {
        return appType;
    }

    public void setAppType(String appType) {
        this.appType = appType;
    }

    public int getSessionStart() {
        return sessionStart;
    }

    public void setSessionStart(int sessionStart) {
        this.sessionStart = sessionStart;
    }

    public int getPageStart() {
        return pageStart;
    }

    public void setPageStart(int pageStart) {
        this.pageStart = pageStart;
    }

    public String getCookieId() {
        return cookieId;
    }

    public void setCookieId(String cookieId) {
        this.cookieId = cookieId;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    @Override
    public String toString() {
        return "Payload{" +
                "actionData=" + actionData +
                ", browserData=" + browserData +
                ", sendStart='" + sendStart + '\'' +
                ", contentID='" + contentID + '\'' +
                ", userID='" + userID + '\'' +
                ", sessionCookieID='" + sessionCookieID + '\'' +
                ", appType='" + appType + '\'' +
                ", sessionStart=" + sessionStart +
                ", pageStart=" + pageStart +
                ", cookieId='" + cookieId + '\'' +
                ", deviceId='" + deviceId + '\'' +
                '}';
    }
}
