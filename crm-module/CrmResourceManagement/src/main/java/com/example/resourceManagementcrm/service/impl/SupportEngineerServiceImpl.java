package com.example.resourceManagementcrm.service.impl;

import com.example.resourceManagementcrm.dto.SupportEngineerDto;
import com.example.resourceManagementcrm.entity.ResourceManagement;
import com.example.resourceManagementcrm.entity.SupportEngineer;
import com.example.resourceManagementcrm.repository.ResourceManagementRepostiory;
import com.example.resourceManagementcrm.repository.SupportEngineerRepository;
import com.example.resourceManagementcrm.service.SupportEnginnerService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class SupportEngineerServiceImpl implements SupportEnginnerService {

    @Autowired
    private SupportEngineerRepository supportEngineerRepository;

    @Autowired
    private ResourceManagementRepostiory resourceManagementRepostiory;

    @Override
    public void add(SupportEngineerDto supportEngineerDto) {
        Optional<ResourceManagement> response=resourceManagementRepostiory.findById(supportEngineerDto.getSupervisorEmail());
        if(!response.isPresent())
        {
            return;
        }

        SupportEngineer supportEngineer=new SupportEngineer();
        BeanUtils.copyProperties(supportEngineerDto,supportEngineer);
        supportEngineerRepository.save(supportEngineer);
    }

    @Override
    public List<SupportEngineer> findBySupervisorEmail(String supervisorEmail) {
        return supportEngineerRepository.findBySupervisorEmail(supervisorEmail);
    }
}
