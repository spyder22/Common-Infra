package com.example.authenticationapi.payloads.authentication;

public class LogoutRequest {

    private String userEmail;
    private String appId;



    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    @Override
    public String toString() {
        return "LogoutRequest{" +
                "userEmail='" + userEmail + '\'' +
                ", appId='" + appId + '\'' +
                '}';
    }
}
