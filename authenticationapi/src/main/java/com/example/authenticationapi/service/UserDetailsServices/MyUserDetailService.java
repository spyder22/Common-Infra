package com.example.authenticationapi.service.UserDetailsServices;

import com.example.authenticationapi.entity.Origin;
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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        System.out.println(username+"-----username");

        char appId= username.charAt(0);
        String userEmail=username.substring(1);
        System.out.println("appid-----"+appId);
        System.out.println(userEmail);
        Origin origin=null;

        switch (appId)
        {
            case '1':
                origin=originRepository.findByUserEmailAndAppId(userEmail,"1");

                break;

            case '2':
                origin=originRepository.findByUserEmailAndAppId(userEmail,"2");

                break;

            case '3':
                origin=originRepository.findByUserEmailAndAppId(userEmail,"3");

                break;

            case '4':
                origin=originRepository.findByUserEmailAndAppId(userEmail,"4");

                break;

            case '5':
                origin=originRepository.findByUserEmailAndAppId(userEmail,"5");

                break;

            default:
                System.out.println("something wrong happend");

        }


        return new User(origin.getUserEmail(),origin.getPassword(), new ArrayList<>());
    }
}
