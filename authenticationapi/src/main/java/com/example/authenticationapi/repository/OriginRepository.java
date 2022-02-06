package com.example.authenticationapi.repository;

import com.example.authenticationapi.entity.Origin;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OriginRepository extends MongoRepository<Origin,String> {

    Origin findByUserEmailAndAppId(String userEmail,String appId);
}
