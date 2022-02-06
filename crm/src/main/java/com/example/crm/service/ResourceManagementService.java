package com.example.crm.service;

import com.example.crm.entity.SupportEngineer;
import com.example.crm.entity.Tickets;

import java.util.List;

public interface ResourceManagementService {


    List<SupportEngineer> getSupportEngineersBySupervisor(String supervisorEmail);
    List<SupportEngineer> getAllActiveSupportEnginnersBySupervisor(String supervisorEmail);
    List<SupportEngineer> getActiveAndAvailableSupportEngineers(String supervisorEmail);
    Boolean inProgressState(String supportPersonEmail);

    void alertSupervisor(String supervisorEmail);
    void alertSupportPerson(String supportPersonEmail);

     void sendTicketToQuee(Tickets ticket);


}
