package com.example.authenticationapi.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Set;

@Document
public class Customer {


    @Id
    private String userEmail;
    private String password;
    private String name;
    private String contact;

    private Set<String> interests;
    private Set<String> appIds;


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

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

    @Override
    public String toString() {
        return "Customer{" +
                "userEmail='" + userEmail + '\'' +
                ", name='" + name + '\'' +
                ", contact='" + contact + '\'' +
                ", interests=" + interests +
                ", appIds=" + appIds +
                '}';
    }
}
