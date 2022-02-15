package com.example.resourceManagementcrm.controller;

import com.example.resourceManagementcrm.payloads.LeadDto;
import com.example.resourceManagementcrm.service.impl.LeadConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/resource")
@CrossOrigin(value = "*",maxAge = 3600)
public class ResourceManagementController {

    @Autowired
    private LeadConsumerService leadConsumerService;


    @PostMapping
    public void sentToRabbit()
    {
        System.out.println("Started");
        LeadDto leadDto=new LeadDto();
        leadDto.setAppId("1");
        leadDto.setCategoryId("2");
        leadDto.setPostUrl("djfisfoij");
        leadDto.setUserEmail("emailuser");
        leadConsumerService.listenToOrganizationPost(leadDto);
        System.out.println("finish");

    }
}
