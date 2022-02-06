package com.example.recomendationapi.service.impl;

import com.example.recomendationapi.dto.CategoryDto;
import com.example.recomendationapi.entity.Category;
import com.example.recomendationapi.repository.CategoryRepository;
import com.example.recomendationapi.service.CategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public String getCategoryNameById(String categoryId) {
        Optional<Category> category=categoryRepository.findById(categoryId);
        if(category.isPresent())
        {
            return category.get().getCategoryName();
        }
        return null;

    }

    @Override
    public void addCategory(CategoryDto categoryDto) {

        Category category=new Category();
        BeanUtils.copyProperties(categoryDto,category);
        categoryRepository.save(category);
    }


    @Override
    public List<String> getAllCategories() {
        List<Category> response= categoryRepository.findAll();
        List<String> categoryNames=new ArrayList<>();
        for (Category category:response)
        {
            categoryNames.add(category.getCategoryName());
        }

        return categoryNames;
    }
}
