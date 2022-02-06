package com.example.recomendationapi.dto;

import java.util.Set;

public class Customer {
    private String userEmail;
    private String name;
    private String contact;
    private Set<String> interests;
    private Set<String> appIds;

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public Set<String> getInterests() {
        return interests;
    }

    public void setInterests(Set<String> interests) {
        this.interests = interests;
    }

    public Set<String> getAppIds() {
        return appIds;
    }

    public void setAppIds(Set<String> appIds) {
        this.appIds = appIds;
    }
}
