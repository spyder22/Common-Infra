package com.example.recomendationapi.controller;


import com.example.recomendationapi.dto.CategoryAddsDto;
import com.example.recomendationapi.service.impl.CategoryAddsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/adds")
public class CategoryAddsController {

    @Autowired
    private CategoryAddsServiceImpl categoryAddsServiceImpl;

    @GetMapping("/all/{categoryId}")
    public List<CategoryAddsDto> getAllAdsByCategoryId(@PathVariable String categoryId)
    {
        System.out.println("getAllAdsByCategoryId");
        return categoryAddsServiceImpl.getAllAddsByCategoryId(categoryId);
    }

    @PostMapping("/")
    public void addAd(@RequestBody CategoryAddsDto categoryAddsDto)
    {
        System.out.println(categoryAddsDto.getCategoryId());
        System.out.println(categoryAddsDto.getUrl());
        categoryAddsServiceImpl.addAd(categoryAddsDto);
    }

    @GetMapping
    public List<CategoryAddsDto> getAll()
    {
        return categoryAddsServiceImpl.getAllAds();
    }

}
