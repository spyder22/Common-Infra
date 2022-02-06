package com.example.authenticationapi.service;

import com.example.authenticationapi.entity.Origin;

public interface OriginService  {

     Origin getByUserEmailAndAppId(String userEmail, String appId);
     void updateOrSaveOrigin(Origin origin);
}
