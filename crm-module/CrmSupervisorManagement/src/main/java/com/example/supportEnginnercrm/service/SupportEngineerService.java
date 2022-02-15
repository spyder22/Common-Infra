package com.example.supportEnginnercrm.service;

import com.example.supportEnginnercrm.dto.SupportEngineerDto;

import java.util.List;

public interface SupportEngineerService {

    List<SupportEngineerDto> findBySuperviosrEmail(String supervisorEmail);
    List<SupportEngineerDto> findBySupervisorEmailAndAvailable(String supervisorEmail);
    List<SupportEngineerDto> findBySupervisorEmailAndTicketCountLessThan(String supervisorEmail);
    List<SupportEngineerDto> findBySupervisorEmailAndAvailableAndTicketCountLessThan(String supervisorEmail);
    void addSupportEngineer(SupportEngineerDto supportEngineerDto);
    List<SupportEngineerDto> findAllBySupervisorEmailOrderByTicketCountAsc(String supervisorEmail);
    void decreaseTicketCountOfSupportEnginner(String supportEnginnerEmail);
    void increaseTicketCountOfSupportEnginner(String supportEnginnerEmail);
    void setOnline(String supportEngineerEmail);
    void setOffline(String supportEngineerEmail);


}
