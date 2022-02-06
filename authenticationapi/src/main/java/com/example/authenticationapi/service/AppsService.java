package com.example.authenticationapi.service;

import com.example.authenticationapi.dto.AppDto;

public interface AppsService {

    void addApp(AppDto appDto);
    String getAddName(String appId);

}
