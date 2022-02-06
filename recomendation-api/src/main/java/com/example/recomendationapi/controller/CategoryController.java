package com.example.recomendationapi.controller;

import com.example.recomendationapi.dto.CategoryDto;
import com.example.recomendationapi.service.impl.CategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryServiceImpl categoryServiceImpl;


    @PostMapping("/add")
    public void addCategory(@RequestBody CategoryDto categoryDto)
    {
        categoryServiceImpl.addCategory(categoryDto);
    }


    @GetMapping("/find/{categoryId}")
    public String getCategoryById(@PathVariable String categoryId)
    {
       return categoryServiceImpl.getCategoryNameById(categoryId);
    }

    @GetMapping("/findall")
    public List<String> getAllCategories()
    {
        return categoryServiceImpl.getAllCategories();
    }

}
