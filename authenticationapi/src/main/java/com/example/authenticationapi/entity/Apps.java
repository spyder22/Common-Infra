package com.example.authenticationapi.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Set;

@Document
public class Apps {

    @Id
    private String appId;
    private String appName;
    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    @Override
    public String toString() {
        return "Apps{" +
                "appId='" + appId + '\'' +
                ", appName='" + appName + '\'' +
                '}';
    }
}



