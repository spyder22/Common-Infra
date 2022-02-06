package com.example.recomendationapi.service.impl;

import com.example.recomendationapi.dto.CategoryAddsDto;
import com.example.recomendationapi.entity.CategoryAdds;
import com.example.recomendationapi.repository.CategoryAddsRepository;
import com.example.recomendationapi.service.CategoryAddsService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryAddsServiceImpl implements CategoryAddsService {
    @Autowired
    private CategoryAddsRepository categoryAddsRepository;

    @Transactional
    public void insertAdd(CategoryAddsDto categoryAddsDto)
    {
        CategoryAdds categoryAdds=new CategoryAdds();
        BeanUtils.copyProperties(categoryAddsDto,categoryAdds);
        categoryAddsRepository.save(categoryAdds);
    }

    @Override
    public List<CategoryAddsDto> getAllAddsByCategoryId(String categoryId)
    {
        List<CategoryAdds> response= categoryAddsRepository.findAllByCategoryId(categoryId);
        List<CategoryAddsDto> categoryAddsDtos=new ArrayList<>();

        for(CategoryAdds categoryAdds:response)
        {
            CategoryAddsDto categoryAddsDto=new CategoryAddsDto();
            BeanUtils.copyProperties(categoryAdds,categoryAddsDto);
            categoryAddsDtos.add(categoryAddsDto);
        }

        return categoryAddsDtos;
    }

    @Override
    public void addAd(CategoryAddsDto categoryAddsDto)
    {
        CategoryAdds categoryAdds=new CategoryAdds();
        BeanUtils.copyProperties(categoryAddsDto,categoryAdds);
        categoryAddsRepository.save(categoryAdds);

    }

    @Override
    public List<CategoryAddsDto> getAllAds()
    {
        List<CategoryAdds> response= categoryAddsRepository.findAll();
        List<CategoryAddsDto> categoryAddsDtos=new ArrayList<>();

        for(CategoryAdds categoryAdds:response)
        {
            CategoryAddsDto categoryAddsDto=new CategoryAddsDto();
            BeanUtils.copyProperties(categoryAdds,categoryAddsDto);
            categoryAddsDtos.add(categoryAddsDto);
        }

        return categoryAddsDtos;
    }
}
