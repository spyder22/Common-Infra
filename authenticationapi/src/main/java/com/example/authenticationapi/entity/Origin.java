package com.example.authenticationapi.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Set;

@Document
public class Origin {

    @Id
    private String originId;
    private String userEmail;
    private String password;
    private String appId;
    private Set<String> deviceTokens;
    private String profileUrl;
    private String profileType;
    private String token;
    private Boolean isAdmin;  // -> only for quiz apps

    public String getProfileUrl() {
        return profileUrl;
    }

    public void setProfileUrl(String profileUrl) {
        this.profileUrl = profileUrl;
    }

    public String getProfileType() {
        return profileType;
    }

    public void setProfileType(String profileType) {
        this.profileType = profileType;
    }

    public String getOriginId() {
        return originId;
    }

    public void setOriginId(String originId) {
        this.originId = originId;
    }

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

    public Set<String> getDeviceTokens() {
        return deviceTokens;
    }

    public void setDeviceTokens(Set<String> deviceTokens) {
        this.deviceTokens = deviceTokens;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Boolean getAdmin() {
        return isAdmin;
    }

    public void setAdmin(Boolean admin) {
        isAdmin = admin;
    }

    @Override
    public String toString() {
        return "Origin{" +
                "originId='" + originId + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", appId='" + appId + '\'' +
                ", deviceTokens=" + deviceTokens +
                ", token='" + token + '\'' +
                '}';
    }
}
