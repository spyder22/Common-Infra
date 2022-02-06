package com.example.recomendationapi.controller;

import com.example.recomendationapi.payloads.response.UserAdUrl;
import com.example.recomendationapi.service.impl.RecommendationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/recommend")
@CrossOrigin(value = "*",maxAge = 3600)
public class RecommendationController {
    @Autowired
    RecommendationServiceImpl recommendationServiceImpl;

    @GetMapping("/{userEmail}")
    public UserAdUrl getRecommendationForUsers(@PathVariable String userEmail)
    {
        System.out.println("getRecommendationForUsers--"+userEmail);
        return  recommendationServiceImpl.getRecommendations(userEmail);
    }
}
