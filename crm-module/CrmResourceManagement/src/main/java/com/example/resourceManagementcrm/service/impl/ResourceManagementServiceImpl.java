package com.example.resourceManagementcrm.service.impl;

import com.example.resourceManagementcrm.dto.ResourceManagementDto;
import com.example.resourceManagementcrm.entity.ResourceManagement;
import com.example.resourceManagementcrm.payloads.SupervisorStatus;
import com.example.resourceManagementcrm.payloads.SupervisorSupport;
import com.example.resourceManagementcrm.repository.ResourceManagementRepostiory;
import com.example.resourceManagementcrm.service.ResourceManagementService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ResourceManagementServiceImpl implements ResourceManagementService {
    @Autowired
    private ResourceManagementRepostiory resourceManagementRepostiory;

    @Override
    public Set<String> findSupportEngineersBySupervisor(String supervisorEmail) {
        return null;
    }

    @Override
    public List<String> findByCategory(String category) {
        List<String> supervisorEmails=new ArrayList<>();
        List<ResourceManagement> resourceManagementList=resourceManagementRepostiory.findByCategoryId(category);
        for(ResourceManagement resourceManagement:resourceManagementList)
        {
            supervisorEmails.add(resourceManagement.getSupervisorEmail());
        }

        return supervisorEmails;    }

    @Override
    public void addSupervisor(ResourceManagementDto resourceManagementDto) {
        ResourceManagement resourceManagement=new ResourceManagement();
        BeanUtils.copyProperties(resourceManagementDto,resourceManagement);
        resourceManagementRepostiory.save(resourceManagement);
    }

    @Override
    public void setStatus(SupervisorStatus supervisorStatus) {
        Optional<ResourceManagement> response=resourceManagementRepostiory.findById(supervisorStatus.getSupervisorEmail());
        if(response.isPresent())
        {
            ResourceManagement resourceManagement=response.get();
            resourceManagement.setOnline(supervisorStatus.isOnline());
            resourceManagementRepostiory.save(resourceManagement);
        }

    }

    @Override
    public Boolean isSupervisorOnline(String supervisorEmail) {
        Optional<ResourceManagement> response=resourceManagementRepostiory.findById(supervisorEmail);
        if(response.isPresent())
        {
            return response.get().getOnline();
        }
        return false;
    }

    @Override
    public void addSupportEnginner(SupervisorSupport supervisorSupport) {

    }

    @Override
    public List<String> findAvailableSupervisorByCategory(String category) {

        List<String> supervisorEmails=new ArrayList<>();
        List<ResourceManagement> resourceManagementList=resourceManagementRepostiory.findByCategoryIdAndOnline(category,true);
        for(ResourceManagement resourceManagement:resourceManagementList)
        {
            supervisorEmails.add(resourceManagement.getSupervisorEmail());
        }

        return supervisorEmails;

    }
}
