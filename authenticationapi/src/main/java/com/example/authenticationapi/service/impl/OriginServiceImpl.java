package com.example.authenticationapi.service.impl;

import com.example.authenticationapi.entity.Origin;
import com.example.authenticationapi.repository.OriginRepository;
import com.example.authenticationapi.service.OriginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
