package com.example.authenticationapi.payloads.requests;

import java.io.Serializable;
import java.sql.Timestamp;

public class LoginLogoutTimeStamp implements Serializable {

    private String userEmail;
    private String status;
    private String appName;
    private Timestamp timestamp;

    public LoginLogoutTimeStamp() {
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "LoginLogoutTimeStamp{" +
                "userEmail='" + userEmail + '\'' +
                ", status='" + status + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}
