package com.example.authenticationapi.service.impl;

import com.example.authenticationapi.entity.Origin;
import com.example.authenticationapi.payloads.requests.DeviceIdsRequest;
import com.example.authenticationapi.payloads.response.DeviceIdsResponse;
import com.example.authenticationapi.repository.OriginRepository;
import com.example.authenticationapi.service.OriginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class OriginServiceImpl implements OriginService {

    @Autowired
    private OriginRepository originRepository;

    @Override
    public Origin getByUserEmailAndAppId(String userEmail,String appId)
    {
        return originRepository.findByUserEmailAndAppId(userEmail,appId);
    }
    @Override
    public void updateOrSaveOrigin(Origin origin)
    {
        originRepository.save(origin);
    }


    public List<DeviceIdsResponse> findAllByUserEmail(DeviceIdsRequest deviceIdsRequest)
    {
        List<Origin> origins=originRepository.findAllByUserEmailAndAppId(deviceIdsRequest.getUserEmails(),deviceIdsRequest.getAppId());

        List<DeviceIdsResponse> deviceIdsResponseList=new ArrayList<>();

        for(Origin origin:origins)
        {
            DeviceIdsResponse deviceIdsResponse=new DeviceIdsResponse();
            List<String> tokens=(List)origin.getDeviceTokens();
            if(tokens!=null)
            {
                deviceIdsResponse.setDeviceId(tokens.get(0));
                deviceIdsResponse.setUserEmail(origin.getUserEmail());
                deviceIdsResponseList.add(deviceIdsResponse);
            }
        }
        return deviceIdsResponseList;
    }

    public List<String> findAllByUserEmailAndDeviceId(DeviceIdsRequest deviceIdsRequest)
    {

        List<String> userEmails=deviceIdsRequest.getUserEmails();
        String appId=deviceIdsRequest.getAppId();
        List<String> deviceIds=new ArrayList<>();

        for(String userEmail:userEmails)
        {

            Origin origin=getByUserEmailAndAppId(userEmail,appId);
            if(origin!=null)
            {
                Set<String> hashSet=origin.getDeviceTokens();
                if(hashSet!=null)
                {
                    System.out.println("findALl by user email devcice id-------"+userEmail);
                    ArrayList<String> tokens  = (ArrayList<String>)hashSet.stream().collect(Collectors.toList());
                    System.out.println(tokens);

                    if(tokens!=null)
                    {
                        deviceIds.add(tokens.get(0));
                    }
                }
            }
        }
        deviceIds.removeIf(Objects::isNull);
        System.out.println(deviceIds+"----------------before sending");
        return deviceIds;

    }

}
