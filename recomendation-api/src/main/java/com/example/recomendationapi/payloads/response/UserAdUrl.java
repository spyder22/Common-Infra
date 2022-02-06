package com.example.recomendationapi.payloads.response;

import com.example.recomendationapi.dto.CategoryAddsDto;

import java.util.List;

public class UserAdUrl {
    private String userEmail;
    private List<CategoryAddsDto> categoryAddsDto;

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public List<CategoryAddsDto> getCategoryAddsDto() {
        return categoryAddsDto;
    }

    public void setCategoryAddsDto(List<CategoryAddsDto> categoryAddsDto) {
        this.categoryAddsDto = categoryAddsDto;
    }
}
