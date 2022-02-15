package com.example.resourceManagementcrm.dto;

import com.example.resourceManagementcrm.entity.SupportEngineer;

import java.util.Set;

public class ResourceManagementDto {

    private String supervisorEmail;
    private String name;

    private Set<String> supportEngineers;

    private String deviceId;
    private Boolean online;
    private String categoryId;

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getSupervisorEmail() {
        return supervisorEmail;
    }

    public void setSupervisorEmail(String supervisorEmail) {
        this.supervisorEmail = supervisorEmail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<String> getSupportEngineers() {
        return supportEngineers;
    }

    public void setSupportEngineers(Set<String> supportEngineers) {
        this.supportEngineers = supportEngineers;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public Boolean getOnline() {
        return online;
    }

    public void setOnline(Boolean online) {
        this.online = online;
    }
}
