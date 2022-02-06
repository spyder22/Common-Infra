package com.example.recomendationapi.service;

import com.example.recomendationapi.dto.CategoryDto;

import java.util.List;

public interface CategoryService {

    String getCategoryNameById(String categoryId);
    void addCategory(CategoryDto categoryDto);
    List<String> getAllCategories();
}
