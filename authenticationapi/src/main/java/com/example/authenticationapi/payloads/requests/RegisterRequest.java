package com.example.authenticationapi.payloads.requests;

import java.util.Set;

public class RegisterRequest {


    private String userEmail;
    private String password;
    private String name;
    private String contact;
    private String appId;
    private Boolean isAdmin;  //-> only for quizapps

    private Set<String> interests;
    private String profileType;
    private String profileUrl;

    public String getProfileType() {
        return profileType;
    }

    public void setProfileType(String profileType) {
        this.profileType = profileType;
    }

    public Set<String> getInterests() {
        return interests;
    }

    public void setInterests(Set<String> interests) {
        this.interests = interests;
    }

    public String getProfileUrl() {
        return profileUrl;
    }

    public void setProfileUrl(String profileUrl) {
        this.profileUrl = profileUrl;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public Boolean getAdmin() {
        return isAdmin;
    }

    public void setAdmin(Boolean admin) {
        isAdmin = admin;
    }

    @Override
    public String toString() {
        return "RegisterRequest{" +
                "userEmail='" + userEmail + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", contact='" + contact + '\'' +
                ", appId='" + appId + '\'' +
                ", isAdmin=" + isAdmin +
                ", interests=" + interests +
                ", profileType='" + profileType + '\'' +
                ", profileUrl='" + profileUrl + '\'' +
                '}';
    }
}
