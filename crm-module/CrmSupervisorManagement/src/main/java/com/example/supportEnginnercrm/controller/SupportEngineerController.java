package com.example.supportEnginnercrm.controller;


import com.example.supportEnginnercrm.dto.SupportEngineerDto;
import com.example.supportEnginnercrm.service.impl.SupportEngineerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/support")
@CrossOrigin(value = "*",maxAge = 3600)
public class SupportEngineerController {
    @Autowired
    private SupportEngineerServiceImpl supportEngineerServiceImpl;


    @GetMapping("/all/{supervisorEmail}")
    public List<SupportEngineerDto> findAllSupportEngineerBySupervisor(@PathVariable String supervisorEmail)
    {
        System.out.println("findAllSupportEngineerBySupervisor-----"+supervisorEmail);
        List<SupportEngineerDto> supportEngineerDtoList= supportEngineerServiceImpl.findBySuperviosrEmail(supervisorEmail);
        System.out.println(supportEngineerDtoList);
        return supportEngineerDtoList;
    }

    @GetMapping("/online/{supervisorEmail}")
    public List<SupportEngineerDto> findAllAvailableSupportEngineerBySupervisor(@PathVariable String supervisorEmail)
    {
        System.out.println("findAllSupportEngineerBySupervisor-----"+supervisorEmail);
        List<SupportEngineerDto> supportEngineerDtoList= supportEngineerServiceImpl.findBySupervisorEmailAndAvailable(supervisorEmail);
        System.out.println(supportEngineerDtoList);
        return supportEngineerDtoList;
    }

    @PostMapping("/increasecount/{supportEngineerMail}")
    public void increaseTicketCount(@PathVariable String supportEngineerMail)
    {
        supportEngineerServiceImpl.increaseTicketCountOfSupportEnginner(supportEngineerMail);
    }


    @PostMapping("/decreasecount/{supportEngineerMail}")
    public void decreaseTicketCount(@PathVariable String supportEngineerMail)
    {
        supportEngineerServiceImpl.decreaseTicketCountOfSupportEnginner(supportEngineerMail);
    }


    @PostMapping("/save")
    public void saveSupportEngineer(@RequestBody SupportEngineerDto supportEngineerDto)
    {
        supportEngineerServiceImpl.addSupportEngineer(supportEngineerDto);
    }

    @PostMapping("/online/{supportEngineerMail}")
    public void setOnline(@PathVariable String supportEngineerMail)
    {
        supportEngineerServiceImpl.setOnline(supportEngineerMail);
    }

    @PostMapping("/offline/{supportEngineerMail}")
    public void setOffline(@PathVariable String supportEngineerMail)
    {
        supportEngineerServiceImpl.setOnline(supportEngineerMail);
    }





}
