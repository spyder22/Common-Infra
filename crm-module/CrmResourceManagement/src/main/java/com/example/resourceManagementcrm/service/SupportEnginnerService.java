package com.example.resourceManagementcrm.service;

import com.example.resourceManagementcrm.dto.SupportEngineerDto;
import com.example.resourceManagementcrm.entity.SupportEngineer;

import java.util.List;

public interface SupportEnginnerService {

    void add(SupportEngineerDto supportEngineerDto);
    List<SupportEngineer> findBySupervisorEmail(String supervisorEmail);

}
