package com.example.crm.repository;

import com.example.crm.entity.ResourceManagement;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResourceManagementRepository extends CrudRepository<ResourceManagement,String> {
}
