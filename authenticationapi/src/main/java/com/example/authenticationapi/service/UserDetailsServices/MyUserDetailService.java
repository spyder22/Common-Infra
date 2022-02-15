package com.example.authenticationapi.service.UserDetailsServices;

import com.example.authenticationapi.entity.Customer;
import com.example.authenticationapi.repository.CustomerRepository;
import com.example.authenticationapi.repository.OriginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MyUserDetailService   implements UserDetailsService {


    @Autowired
    private OriginRepository originRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Customer customer=customerRepository.findByUserEmail(username);
        return new User(username,customer.getPassword(), new ArrayList<>());

    }
}
