package com.example.authenticationapi.payloads.requests;

import java.util.Set;

public class UpdateInterestRequest {
    private String userEmail;
    private String appId;
    private Set<String> interests; //-->will have categoryids

    public UpdateInterestRequest() {
    }

    public UpdateInterestRequest(String userEmail, String appId, Set<String> interests) {
        this.userEmail = userEmail;
        this.appId = appId;
        this.interests = interests;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
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
