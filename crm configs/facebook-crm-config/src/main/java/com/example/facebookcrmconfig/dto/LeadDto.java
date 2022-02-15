package com.example.facebookcrmconfig.dto;

public class LeadDto {

    private String userEmail;
    private String postUrl;
    private String categoryId;
    private String appId;



    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

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

    @Override
    public String toString() {
        return "LeadDto{" +
                "userEmail='" + userEmail + '\'' +
                ", postUrl='" + postUrl + '\'' +
                ", categoryId='" + categoryId + '\'' +
                '}';
    }
}
