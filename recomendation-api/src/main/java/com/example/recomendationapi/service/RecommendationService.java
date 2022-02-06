package com.example.recomendationapi.service;

import com.example.recomendationapi.payloads.request.OrganizationPost;
import com.example.recomendationapi.payloads.response.UserAdUrl;

public interface RecommendationService {
    UserAdUrl getRecommendations(String userEmail);
    void listenToOrganizationPost(OrganizationPost organizationPost);
}
