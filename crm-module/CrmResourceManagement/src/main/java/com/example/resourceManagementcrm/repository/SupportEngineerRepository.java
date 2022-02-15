package com.example.resourceManagementcrm.repository;

import com.example.resourceManagementcrm.entity.SupportEngineer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SupportEngineerRepository extends CrudRepository<SupportEngineer,String> {
    List<SupportEngineer> findBySupervisorEmail(String supervisorEmail);
}
