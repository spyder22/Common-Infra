package com.example.supportenginner1.dto;

import java.sql.Timestamp;

public class TicketsDto {

    private Long ticketId;
    private String leadMail;
    private String postUrl;
    private String categoryId;
    private String appId;
    private String supportPersonEmail;
    private String supervisorEmail;

    private Timestamp createdTime;
    private Timestamp endedTime;
    private String comment;

    public String getSupervisorEmail() {
        return supervisorEmail;
    }

    public void setSupervisorEmail(String supervisorEmail) {
        this.supervisorEmail = supervisorEmail;
    }

    public Long getTicketId() {
        return ticketId;
    }

    public void setTicketId(Long ticketId) {
        this.ticketId = ticketId;
    }

    public String getLeadMail() {
        return leadMail;
    }

    public void setLeadMail(String leadMail) {
        this.leadMail = leadMail;
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
