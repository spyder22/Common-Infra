package com.example.supportEnginnercrm.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table
@Entity
public class SupportEnginner {

    @Id
    private String supportPersonEmail;
    private String supervisorEmail;
    private String name;
    private String deviceId;
    private Boolean online; // -> offline/online
    private Boolean available;  //->true/false
    private int ticketCount;

    public int getTicketCount() {
        return ticketCount;
    }

    public void setTicketCount(int ticketCount) {
        this.ticketCount = ticketCount;
    }

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

    public Boolean getOnline() {
        return online;
    }

    public void setOnline(Boolean online) {
        this.online = online;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public String getSupervisorEmail() {
        return supervisorEmail;
    }

    public void setSupervisorEmail(String supervisorEmail) {
        this.supervisorEmail = supervisorEmail;
    }
}
