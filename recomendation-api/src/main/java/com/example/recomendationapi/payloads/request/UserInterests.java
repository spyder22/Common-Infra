package com.example.recomendationapi.payloads.request;

import java.util.Set;

public class UserInterests {
    private String userEmail;
    private Set<String> interests;

    public UserInterests() {
    }

    public UserInterests(String userEmail, Set<String> interests) {
        this.userEmail = userEmail;
        this.interests = interests;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public Set<String> getInterests() {
        return interests;
    }

    public void setInterests(Set<String> interests) {
        this.interests = interests;
    }
}
