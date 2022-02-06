package com.example.crm.dto;

public class SupportEngineerDto {
    private String supportPersonEmail;
    private String name;
    private String deviceId;
    private Boolean available;

    public String getSupportPersonEmail() {
        return supportPersonEmail;
    }

    public void setSupportPersonEmail(String supportPersonEmail) {
        this.supportPersonEmail = supportPersonEmail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
