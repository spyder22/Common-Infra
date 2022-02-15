package com.example.resourceManagementcrm.service;

import com.example.resourceManagementcrm.dto.ResourceManagementDto;
import com.example.resourceManagementcrm.payloads.SupervisorStatus;
import com.example.resourceManagementcrm.payloads.SupervisorSupport;

import java.util.List;
import java.util.Set;

public interface ResourceManagementService {

    Set<String> findSupportEngineersBySupervisor(String supervisorEmail);
    List<String> findByCategory(String categoryId);

    void addSupervisor(ResourceManagementDto resourceManagementDto);
    void setStatus(SupervisorStatus supervisorStatus);

    Boolean isSupervisorOnline(String supervisorEmail);
    void addSupportEnginner(SupervisorSupport supervisorSupport);
    List<String> findAvailableSupervisorByCategory(String category);
}
