package com.example.authenticationapi.service;

import com.example.authenticationapi.entity.Customer;

import java.util.List;
import java.util.Set;

public interface CustomerService {
     Customer getByUserEmail(String userEmail);
     void updateOrSaveCustomer(Customer customer);
     List<Customer> findAllByIds(Set<String> ids);
}
