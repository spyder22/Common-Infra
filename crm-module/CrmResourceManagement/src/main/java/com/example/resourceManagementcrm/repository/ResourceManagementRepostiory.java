package com.example.resourceManagementcrm.repository;

import com.example.resourceManagementcrm.entity.ResourceManagement;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResourceManagementRepostiory extends CrudRepository<ResourceManagement,String> {
    List<ResourceManagement> findByCategoryIdAndOnline(String categoryId,Boolean online);
    List<ResourceManagement> findByCategoryId(String categoryId);
}
