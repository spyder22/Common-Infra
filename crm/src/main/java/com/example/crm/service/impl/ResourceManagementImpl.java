package com.example.crm.service.impl;

import com.example.crm.entity.ResourceManagement;
import com.example.crm.entity.SupportEngineer;
import com.example.crm.entity.Tickets;
import com.example.crm.repository.ResourceManagementRepository;
import com.example.crm.service.ResourceManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class ResourceManagementImpl implements ResourceManagementService {

    @Autowired
    ResourceManagementRepository resourceManagementRepository;
    @Override
    public List<SupportEngineer> getSupportEngineersBySupervisor(String supervisorEmail) {

        Optional<ResourceManagement> resourceManagement=resourceManagementRepository.findById(supervisorEmail);
        if(resourceManagement.isPresent())
        {
            resourceManagement.get();
        }
        return null;
    }

    @Override
    public List<SupportEngineer> getAllActiveSupportEnginnersBySupervisor(String supervisorEmail) {
        return null;
    }

    @Override
    public List<SupportEngineer> getActiveAndAvailableSupportEngineers(String supervisorEmail) {
        return null;
    }

    @Override
    public Boolean inProgressState(String supportPersonEmail) {
        return null;
    }

    @Override
    public void alertSupervisor(String supervisorEmail) {

    }

    @Override
    public void alertSupportPerson(String supportPersonEmail) {

    }

    @Override
    public void sendTicketToQuee(Tickets ticket) {

    }
}
