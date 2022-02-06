package com.example.authenticationapi.controller;

import com.example.authenticationapi.entity.Customer;
import com.example.authenticationapi.entity.Origin;
import com.example.authenticationapi.payloads.authentication.AuthenticationRequest;
import com.example.authenticationapi.payloads.authentication.AuthenticationResponse;
import com.example.authenticationapi.payloads.authentication.LogoutRequest;
import com.example.authenticationapi.payloads.requests.*;
import com.example.authenticationapi.payloads.response.RegisterResponse;
import com.example.authenticationapi.payloads.response.UserProfileResponse;
import com.example.authenticationapi.service.UserDetailsServices.*;
import com.example.authenticationapi.service.impl.AppsServiceImpl;
import com.example.authenticationapi.service.impl.CustomerServiceImpl;
import com.example.authenticationapi.service.impl.OriginServiceImpl;
import com.example.authenticationapi.util.JwtUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@RestController
@RequestMapping("/authenticate")
@CrossOrigin(value = "*",maxAge = 3600)
public class AuthenticationController {


    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtTokenUtil;

    @Autowired
    private MyUserDetailService myUserDetailService;


    @Autowired
    private OriginServiceImpl originServiceImpl;

    @Autowired
    private CustomerServiceImpl customerServiceImpl;

    @Autowired
    private AppsServiceImpl appsServiceImpl;


    @Autowired
    private KafkaTemplate<String, LoginLogoutTimeStamp> kafkaTemplate;

    public static final String topic="loginlogout";



    //authenticate api that takes username and password and returns the JWT Token as a response
    @PostMapping(value = "/login" )
    public ResponseEntity<?> login(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {

        System.out.println("******************login***************");
        System.out.println(authenticationRequest);

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getAppId()+authenticationRequest.getUserEmail(), authenticationRequest.getPassword())
            );
        }
        catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }


        String appId=authenticationRequest.getAppId();

        UserDetails userDetails=null;

        switch (appId)
        {
            case "1":
                userDetails = myUserDetailService
                        .loadUserByUsername("1"+authenticationRequest.getUserEmail());
                break;

            case "2":
                System.out.println("22");
                userDetails = myUserDetailService
                        .loadUserByUsername("2"+authenticationRequest.getUserEmail());
                break;

            case "3":
                userDetails = myUserDetailService
                        .loadUserByUsername("3"+authenticationRequest.getUserEmail());
                break;

            case "4":
                userDetails = myUserDetailService
                        .loadUserByUsername("4"+authenticationRequest.getUserEmail());
                break;

            case "5":
                userDetails = myUserDetailService
                        .loadUserByUsername("5"+authenticationRequest.getUserEmail());
                break;

             default:
                 throw new Exception("Incorrect appId");
        }

        final String jwt = jwtTokenUtil.generateToken(userDetails);
        Origin origin=originServiceImpl.getByUserEmailAndAppId(authenticationRequest.getUserEmail(),authenticationRequest.getAppId());

        Set<String> deviceTokens=origin.getDeviceTokens();
        if(deviceTokens!=null)
        {
           deviceTokens.add(authenticationRequest.getDeviceId());
        }
        else
        {
            deviceTokens=new HashSet<>();
            deviceTokens.add(authenticationRequest.getDeviceId());

        }


        origin.setDeviceTokens(deviceTokens);
        origin.setToken(jwt);
        originServiceImpl.updateOrSaveOrigin(origin);

        sendTimeStamp(authenticationRequest.getUserEmail(),"Login",appId);

        // TODO: 04/02/22 send login status of user to analytic team in a queue
        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }

    public void sendTimeStamp(String userEmail,String status,String appId)
    {
        LoginLogoutTimeStamp loginLogoutTimeStamp=new LoginLogoutTimeStamp();
        Long datetime = System.currentTimeMillis();
        Timestamp timestamp = new Timestamp(datetime);
        loginLogoutTimeStamp.setTimestamp(timestamp);
        loginLogoutTimeStamp.setStatus(status);
        loginLogoutTimeStamp.setUserEmail(userEmail);
        loginLogoutTimeStamp.setAppName(appsServiceImpl.getAddName(appId));

        // TODO: 06/02/22 publish to queue
        kafkaTemplate.send(topic,loginLogoutTimeStamp);
        System.out.println("***************sent to loginlogouttimestamp*************");

    }




    @PostMapping(value = "/logout")
    public ResponseEntity<?> logout(@RequestBody  LogoutRequest logoutRequest)
    {
        System.out.println(logoutRequest);
        Origin origin=originServiceImpl.getByUserEmailAndAppId(logoutRequest.getUserEmail(),logoutRequest.getAppId());
        if(origin!=null)
        {
            origin.setToken(null);
            originServiceImpl.updateOrSaveOrigin(origin);
            // TODO: 06/02/22 pass to kafka queue
            sendTimeStamp(logoutRequest.getUserEmail(),"Logout",logoutRequest.getAppId());
            return ResponseEntity.ok(new RegisterResponse("success"));
        }
        return ResponseEntity.ok(new RegisterResponse("failure"));
    }


    @PostMapping(value = "/register" )
    public ResponseEntity<?> register(@RequestBody RegisterRequest registerRequest) throws Exception {

        System.out.println("****************Register*****************");
        System.out.println(registerRequest);
        Origin origin=originServiceImpl.getByUserEmailAndAppId(registerRequest.getUserEmail(),registerRequest.getAppId());

        if(origin!=null)
        {
//            throw  new Exception("User already registered");
            return ResponseEntity.ok(new RegisterResponse("failure"));
        }
        else
        {
            origin=new Origin();
        }

        BeanUtils.copyProperties(registerRequest,origin);
        Customer customer=customerServiceImpl.getByUserEmail(registerRequest.getUserEmail());

        if(customer==null)
        {
            customer=new Customer();
            BeanUtils.copyProperties(registerRequest,customer);
            Set<String> appIds=new HashSet<>();
            appIds.add(registerRequest.getAppId());
            customer.setAppIds(appIds);
            customerServiceImpl.updateOrSaveCustomer(customer);
        }
        else
        {
            Set<String> appIds=customer.getAppIds();
            appIds.add(registerRequest.getAppId());
            customer.setAppIds(appIds);

        }
        if(registerRequest.getInterests()!=null)
        {
            updateInterest(new UpdateInterestRequest(registerRequest.getUserEmail(),registerRequest.getAppId(),registerRequest.getInterests()));
        }
        origin.setProfileUrl(registerRequest.getProfileUrl());
        origin.setProfileType(registerRequest.getProfileType());

        customerServiceImpl.updateOrSaveCustomer(customer);
        originServiceImpl.updateOrSaveOrigin(origin);
        return ResponseEntity.ok(new RegisterResponse("success"));
    }


    @PostMapping(value = "/interest" )
    public void updateInterest(@RequestBody UpdateInterestRequest updateInterestRequest) throws Exception {
        Customer customer=customerServiceImpl.getByUserEmail(updateInterestRequest.getUserEmail());


        Set<String> updatedInterests=updateInterestRequest.getInterests();
        if(customer.getInterests()==null)
        {
            customer.setInterests(updatedInterests);
            System.out.println("***********adding interest done*************");
        }
        else
        {
            Set<String> oldInterests=customer.getInterests();
            oldInterests.addAll(updatedInterests);
            customer.setInterests(oldInterests);
        }
        customerServiceImpl.updateOrSaveCustomer(customer);
        System.out.println("************Interest updated successfully***************");
    }

    @GetMapping(value = "/interest/{userEmail}" )
    public Set<String> getInterestByMail(@PathVariable String userEmail)
    {
        Customer customer=customerServiceImpl.getByUserEmail(userEmail);
        return customer.getInterests();
    }

    @GetMapping(value = "/appid" )
    public boolean isRegisteredOnAppId(@RequestBody AppRegistered appRegistered)
    {
        Customer customer=customerServiceImpl.getByUserEmail(appRegistered.getUserEmail());
        if(customer !=null)
        {
            if(customer.getAppIds().contains(appRegistered.getAppId()))
            {
                return true;
            }
        }
        return false;

    }

    @GetMapping(value = "/appids/{userEmail}" )
    public Set<String> getRegisteredAppsByUser(@PathVariable  String userEmail)
    {
        Customer customer=customerServiceImpl.getByUserEmail(userEmail);
        return customer.getAppIds();
    }


    @PostMapping("/interests")
    public List<Customer> getUsersInterestByMail(@RequestBody Set<String> userEmails)
    {
        System.out.println("getUsersInterestByMail");
        return customerServiceImpl.findAllByIds(userEmails);

    }

    @PostMapping("/interests/{userEmail}")
    public Customer getUserInterestByMail(@PathVariable String userEmail)
    {
        System.out.println("getUsersInterestByMail");
        return customerServiceImpl.findById(userEmail);

    }


    @PostMapping("/profile")
    public UserProfileResponse getUserProfile(@RequestBody UserProfileRequest userProfileRequest)
    {
        Customer customer=customerServiceImpl.getByUserEmail(userProfileRequest.getUserEmail());
        UserProfileResponse userProfileResponse=new UserProfileResponse();
        BeanUtils.copyProperties(customer,userProfileResponse);
        userProfileResponse.setAppId(userProfileRequest.getAppId());
        return userProfileResponse;
    }

}
