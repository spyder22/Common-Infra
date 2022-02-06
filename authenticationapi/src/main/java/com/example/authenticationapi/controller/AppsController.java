package com.example.authenticationapi.controller;

import com.example.authenticationapi.dto.AppDto;
import com.example.authenticationapi.service.impl.AppsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/apps")
public class AppsController {
    @Autowired
    private AppsServiceImpl appsServiceImpl;

    @PostMapping
    public void addApp(@RequestBody AppDto appDto)
    {
        System.out.println("adding");
        appsServiceImpl.addApp(appDto);
    }

    @GetMapping(value = "/{appId}" )
    public String getAppName(@PathVariable String appId)
    {
        return appsServiceImpl.getAddName(appId);
    }

    @GetMapping(value = "/all")
    public List<AppDto> getAllApps()
    {
        return appsServiceImpl.getAll();
    }
}
