package com.example.authenticationapi.service.impl;

import com.example.authenticationapi.dto.AppDto;
import com.example.authenticationapi.entity.Apps;
import com.example.authenticationapi.repository.AppsRepository;
import com.example.authenticationapi.service.AppsService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AppsServiceImpl implements AppsService {

    @Autowired
    private AppsRepository appsRepository;
    @Override
    public void addApp(AppDto appDto) {

        Apps response=appsRepository.findByAppName(appDto.getAppName());

        if(response==null)
        {
            Apps apps=new Apps();
            BeanUtils.copyProperties(appDto,apps);
            appsRepository.save(apps);
        }
    }

    @Override
    public String getAddName(String appId) {
         Optional<Apps> response=appsRepository.findById(appId);
         if(response.isPresent())
         {
             return response.get().getAppName();
         }
         return null;
    }

    public List<AppDto> getAll()
    {
        List<Apps> response=appsRepository.findAll();
        List<AppDto> appDtos=new ArrayList<>();

        for (Apps apps:response)
        {
            AppDto appDto=new AppDto();
            BeanUtils.copyProperties(apps,appDto);
            appDtos.add(appDto);
        }
        return appDtos;
    }
}
