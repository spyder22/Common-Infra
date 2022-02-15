package com.example.authenticationapi.payloads.requests;

import java.util.List;

public class DeviceIdsRequest {
    private String appId;
    private List<String> userEmails;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public List<String> getUserEmails() {
        return userEmails;
    }

    public void setUserEmails(List<String> userEmails) {
        this.userEmails = userEmails;
    }

    @Override
    public String toString() {
        return "DeviceIdsRequest{" +
                "appId='" + appId + '\'' +
                ", userEmails=" + userEmails +
                '}';
    }
}
