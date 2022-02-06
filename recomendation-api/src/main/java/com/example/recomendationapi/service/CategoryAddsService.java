package com.example.recomendationapi.service;

import com.example.recomendationapi.dto.CategoryAddsDto;

import java.util.List;

public interface CategoryAddsService {

     List<CategoryAddsDto> getAllAddsByCategoryId(String categoryId);
     void addAd(CategoryAddsDto categoryAddsDto);
     List<CategoryAddsDto> getAllAds();

}
