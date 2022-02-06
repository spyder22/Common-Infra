package com.example.authenticationapi.service.impl;

import com.example.authenticationapi.entity.Customer;
import com.example.authenticationapi.entity.Origin;
import com.example.authenticationapi.repository.CustomerRepository;
import com.example.authenticationapi.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Customer getByUserEmail(String userEmail)
    {
        return customerRepository.findByUserEmail(userEmail);
    }
    @Override
    public void updateOrSaveCustomer(Customer customer)
    {
        customerRepository.save(customer);
    }

    @Override
    public List<Customer> findAllByIds(Set<String> ids) {
        List<Customer> customers=(List<Customer>) customerRepository.findAllById(ids);

        return customers;
    }

    public Customer findById(String userEmail) {
        Optional<Customer> customer=customerRepository.findById(userEmail);

        if(customer.isPresent())
        {
            return customer.get();
        }
        return null;
    }



}
