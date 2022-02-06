package com.example.recomendationapi.dto;

import java.util.Set;

public class UserAdd {

    private String userEmail;
    private Set<String> addsAddress;

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public Set<String> getAddsAddress() {
        return addsAddress;
    }

    public void setAddsAddress(Set<String> addsAddress) {
        this.addsAddress = addsAddress;
    }
}
