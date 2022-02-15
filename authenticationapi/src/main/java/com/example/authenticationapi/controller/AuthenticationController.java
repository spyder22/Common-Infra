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
import net.minidev.json.JSONObject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.*;


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
    private KafkaTemplate<String, JSONObject> kafkaTemplate;


    public static final String topic="loginlogout";



    //authenticate api that takes username and password and returns the JWT Token as a response
    @PostMapping(value = "/login" )
    public ResponseEntity<?> login(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {

        System.out.println(authenticationRequest);

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUserEmail(), hashPassword(authenticationRequest.getPassword()))
            );
        }
        catch (BadCredentialsException e) {
//            throw new Exception("Incorrect username or password", e);
            return ResponseEntity.ok(null);
        }


        String appId=authenticationRequest.getAppId();
        UserDetails userDetails = myUserDetailService
                        .loadUserByUsername(authenticationRequest.getUserEmail());

        final String jwt = jwtTokenUtil.generateToken(userDetails);
        Origin origin=originServiceImpl.getByUserEmailAndAppId(authenticationRequest.getUserEmail(),authenticationRequest.getAppId());

        Set<String> deviceTokens=origin.getDeviceTokens();
        if(deviceTokens!=null)
        {
            if(!deviceTokens.contains(authenticationRequest.getDeviceId()))
            {
                deviceTokens.add(authenticationRequest.getDeviceId());
            }
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
        Long datetime = System.currentTimeMillis();
        Timestamp timestamp = new Timestamp(datetime);

        JSONObject jsonObject=new JSONObject();
        jsonObject.put("timestamp",timestamp);
        jsonObject.put("userEmail",userEmail);
        jsonObject.put("appName",appsServiceImpl.getAddName(appId));
        jsonObject.put("status",status);

        // TODO: 06/02/22 publish to queue
        kafkaTemplate.send(topic,jsonObject);

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


    private boolean checkIfUserRegistered(String userEmail)
    {
        System.out.println(customerServiceImpl);
        if(customerServiceImpl.findById(userEmail)!=null)
        {
            return true;
        }
        return false;

    }

    @PostMapping(value = "/register" )
    public ResponseEntity<?> register(@RequestBody RegisterRequest registerRequest)  {

        System.out.println(registerRequest);


        if(checkIfUserRegistered((registerRequest.getUserEmail()))==true)
        {
            return ResponseEntity.ok(new RegisterResponse("already registered"));
        }

        String encryptedPassword ;

        try {
            encryptedPassword = hashPassword(registerRequest.getPassword());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        for(int i=1;i<6;i++)
        {
            Origin origin=new Origin();
            BeanUtils.copyProperties(registerRequest,origin);
            origin.setAppId(String.valueOf(i));
            originServiceImpl.updateOrSaveOrigin(origin);

        }
        Customer customer=new Customer();
        BeanUtils.copyProperties(registerRequest,customer);
        customer.setAppIds(new HashSet<>(Arrays.asList("1", "2", "3","4","5")));
        customer.setInterests(registerRequest.getInterests());
        customerServiceImpl.updateOrSaveCustomer(customer);

        return ResponseEntity.ok(new RegisterResponse("success"));
    }


    @PostMapping(value = "/interest" )
    public void updateInterest(@RequestBody UpdateInterestRequest updateInterestRequest) throws Exception {
        System.out.println(updateInterestRequest);


        Customer customer=customerServiceImpl.getByUserEmail(updateInterestRequest.getUserEmail());
        if(customer==null)
        {
            System.out.println("User don't exists");
            return;
        }



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



    @PostMapping("/deviceids")
    public List<String> getDeviceIds(@RequestBody DeviceIdsRequest deviceIdsRequest)
    {
        System.out.println("getDeviceId request comming----------");
        return originServiceImpl.findAllByUserEmailAndDeviceId(deviceIdsRequest);

    }

    @PostMapping("/getdeviceids")
    public List<String> getDeviceIdsFromEmails(@RequestBody DeviceIdsRequest deviceIdsRequest)
    {
        System.out.println("getDeviceId request comming----------");
        System.out.println(deviceIdsRequest);
        return originServiceImpl.findAllByUserEmailAndDeviceId(deviceIdsRequest);
    }

    private String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(password.getBytes());
        byte[] digest = md.digest();
        String hash = DatatypeConverter.printHexBinary(digest).toUpperCase();
        return hash;
    }
}
