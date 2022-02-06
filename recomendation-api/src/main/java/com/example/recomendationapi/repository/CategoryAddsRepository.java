package com.example.recomendationapi.repository;

import com.example.recomendationapi.entity.CategoryAdds;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryAddsRepository extends MongoRepository<CategoryAdds,String> {
    List<CategoryAdds> findAllByCategoryId(String categoryId);
}
