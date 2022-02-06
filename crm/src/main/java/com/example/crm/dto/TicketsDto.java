package com.example.crm.dto;

import java.sql.Timestamp;

public class TicketsDto {
    private String postUrl;
    private String supportPersonEmail;
    private Timestamp createdTime;
    private Timestamp endedTime;
    private String comment;


    public String getPostUrl() {
        return postUrl;
    }

    public void setPostUrl(String postUrl) {
        this.postUrl = postUrl;
    }

    public String getSupportPersonEmail() {
        return supportPersonEmail;
    }

    public void setSupportPersonEmail(String supportPersonEmail) {
        this.supportPersonEmail = supportPersonEmail;
    }

    public Timestamp getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Timestamp createdTime) {
        this.createdTime = createdTime;
    }

    public Timestamp getEndedTime() {
        return endedTime;
    }

    public void setEndedTime(Timestamp endedTime) {
        this.endedTime = endedTime;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
