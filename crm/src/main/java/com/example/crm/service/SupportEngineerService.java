package com.example.crm.service;

import com.example.crm.entity.SupportEngineer;

import java.util.List;

public interface SupportEngineerService {

    void add(SupportEngineer supportEngineer);
    SupportEngineer getSupportEnginnerByEmail(String email);
    List<SupportEngineer> findAllActiveByEmails(List<String> emails);
    List<SupportEngineer> findAllActiveSupportEnginners();
    List<SupportEngineer> findAllActiveAndAvailableSupportEnginners();
    void setStatus(String email);

}
