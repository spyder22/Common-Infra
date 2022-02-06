package com.example.authenticationapi.payloads.authentication;

public class AuthenticationRequest {
    private String userEmail;
    private String password;
    private String appId;
    private String deviceId;


    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    @Override
    public String toString() {
        return "AuthenticationRequest{" +
                "userEmail='" + userEmail + '\'' +
                ", password='" + password + '\'' +
                ", appId='" + appId + '\'' +
                ", deviceId='" + deviceId + '\'' +
                '}';
    }
}
