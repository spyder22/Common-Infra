package com.example.crm.repository;

import com.example.crm.entity.SupportEngineer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SupportEngineerRepository extends CrudRepository<SupportEngineer,String> {
}
