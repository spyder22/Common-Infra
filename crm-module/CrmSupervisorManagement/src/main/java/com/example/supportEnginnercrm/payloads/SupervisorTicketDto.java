package com.example.supportEnginnercrm.payloads;

import java.io.Serializable;

public class SupervisorTicketDto implements Serializable {
    private String supervisorEmail;

    private String postUrl;
    private String categoryId;
    private String appId;
    private String leadMail;

    public String getLeadMail() {
        return leadMail;
    }

    public void setLeadMail(String leadMail) {
        this.leadMail = leadMail;
    }

    public String getSupervisorEmail() {
        return supervisorEmail;
    }

    public void setSupervisorEmail(String supervisorEmail) {
        this.supervisorEmail = supervisorEmail;
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
        return "SupervisorTicketDto{" +
                "supervisorEmail='" + supervisorEmail + '\'' + '\'' +
                ", postUrl='" + postUrl + '\'' +
                ", categoryId='" + categoryId + '\'' +
                ", appId='" + appId + '\'' +
                '}';
    }
}
