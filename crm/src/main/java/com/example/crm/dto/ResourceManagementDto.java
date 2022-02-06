package com.example.crm.dto;

import java.util.List;

public class ResourceManagementDto {


    private String supervisorEmail;
    private String name;
    private List<String> supportPersons;
    private String deviceId;
    private Boolean available;

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

    public List<String> getSupportPersons() {
        return supportPersons;
    }

    public void setSupportPersons(List<String> supportPersons) {
        this.supportPersons = supportPersons;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }
}
