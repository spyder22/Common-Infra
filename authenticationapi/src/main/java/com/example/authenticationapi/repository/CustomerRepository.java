package com.example.authenticationapi.repository;

import com.example.authenticationapi.entity.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends MongoRepository<Customer,String> {
    Customer findByUserEmail(String userEmail);


}
