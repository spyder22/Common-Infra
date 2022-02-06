package com.example.authenticationapi.repository;

import com.example.authenticationapi.entity.Apps;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppsRepository extends MongoRepository<Apps,String> {
    Apps findByAppName(String appName);
}
