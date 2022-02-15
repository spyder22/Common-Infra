package com.example.resourceManagementcrm.payloads;

import java.io.Serializable;

public class LeadDto implements Serializable {

    private String userEmail;
    private String postUrl;
    private String categoryId;
    private String appId;

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getPostUrl() {
        return postUrl;
    }

    public void setPostUrl(String postUrl) {
        this.postUrl = postUrl;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    @Override
    public String toString() {
        return "LeadDto{" +
                "userEmail='" + userEmail + '\'' +
                ", postUrl='" + postUrl + '\'' +
                ", categoryId='" + categoryId + '\'' +
                ", appId='" + appId + '\'' +
                '}';
    }
}
