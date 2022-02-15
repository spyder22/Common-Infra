package com.example.supportEnginnercrm.service.impl;

import com.example.supportEnginnercrm.dto.SupportEngineerDto;
import com.example.supportEnginnercrm.entity.SupportEnginner;
import com.example.supportEnginnercrm.repository.SupportEnginnerRepository;
import com.example.supportEnginnercrm.service.SupportEngineerService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SupportEngineerServiceImpl implements SupportEngineerService {

    @Autowired
    private SupportEnginnerRepository supportEnginnerRepository;


    private List<SupportEngineerDto> convertEntityToDto(List<SupportEnginner> supportEnginners)
    {
        List<SupportEngineerDto> supportEngineerDtos=new ArrayList<>();
        for(SupportEnginner supportEnginner:supportEnginners)
        {
            SupportEngineerDto supportEngineerDto=new SupportEngineerDto();
            BeanUtils.copyProperties(supportEnginner,supportEngineerDto);
            supportEngineerDtos.add(supportEngineerDto);
        }
        return supportEngineerDtos;
    }

    @Override
    public void addSupportEngineer(SupportEngineerDto supportEngineerDto) {
        SupportEnginner supportEnginner=new SupportEnginner();
        BeanUtils.copyProperties(supportEngineerDto,supportEnginner);
        supportEnginnerRepository.save(supportEnginner);
    }

    @Override
    public List<SupportEngineerDto> findBySupervisorEmailAndTicketCountLessThan(String supervisorEmail) {
        return convertEntityToDto(supportEnginnerRepository.findAllBySupervisorEmailAndTicketCountLessThan(supervisorEmail,3));
    }

    @Override
    public List<SupportEngineerDto> findBySuperviosrEmail(String supervisorEmail) {
        return convertEntityToDto(supportEnginnerRepository.findAllBySupervisorEmail(supervisorEmail));
    }

    @Override
    public List<SupportEngineerDto> findBySupervisorEmailAndAvailable(String supervisorEmail) {
        return convertEntityToDto(supportEnginnerRepository.findAllBySupervisorEmailAndAvailable(supervisorEmail,true));
    }

    @Override
    public List<SupportEngineerDto> findBySupervisorEmailAndAvailableAndTicketCountLessThan(String supervisorEmail) {
        return convertEntityToDto(supportEnginnerRepository.findAllBySupervisorEmailAndAvailableAndTicketCountLessThan(supervisorEmail,true,3));
    }

    @Override
    public List<SupportEngineerDto> findAllBySupervisorEmailOrderByTicketCountAsc(String supervisorEmail) {
        return convertEntityToDto(supportEnginnerRepository.findAllBySupervisorEmailOrderByTicketCountAsc(supervisorEmail));
    }

    @Override
    public void decreaseTicketCountOfSupportEnginner(String supportEnginnerEmail) {
        Optional<SupportEnginner> response=supportEnginnerRepository.findById(supportEnginnerEmail);
        if(response.isPresent())
        {
            SupportEnginner supportEnginner=response.get();
            if(supportEnginner.getTicketCount()>0)
            {
                supportEnginner.setTicketCount(supportEnginner.getTicketCount()-1);
                supportEnginnerRepository.save(supportEnginner);
            }
        }
    }

    @Override
    public void increaseTicketCountOfSupportEnginner(String supportEnginnerEmail) {
        Optional<SupportEnginner> response=supportEnginnerRepository.findById(supportEnginnerEmail);
        if(response.isPresent())
        {
            SupportEnginner supportEnginner=response.get();
            supportEnginner.setTicketCount(supportEnginner.getTicketCount()+1);
            supportEnginnerRepository.save(supportEnginner);
        }
    }


    @Override
    public void setOnline(String supportEngineerEmail) {
        Optional<SupportEnginner> response=supportEnginnerRepository.findById(supportEngineerEmail);
        if(response.isPresent())
        {
            SupportEnginner supportEnginner=response.get();
            supportEnginner.setOnline(true);
            supportEnginnerRepository.save(supportEnginner);
        }
    }

    @Override
    public void setOffline(String supportEngineerEmail) {
        Optional<SupportEnginner> response=supportEnginnerRepository.findById(supportEngineerEmail);
        if(response.isPresent())
        {
            SupportEnginner supportEnginner=response.get();
            supportEnginner.setOnline(false);
            supportEnginnerRepository.save(supportEnginner);
        }
    }
}
