package com.example.instacrmconfig.service;

import com.example.instacrmconfig.dto.LeadDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class InstaService {


    private static final String topic="leadinsta";
    @Autowired
    public KafkaTemplate<String, LeadDto> kafkaTemplate;


    @KafkaListener(topics = "crmm",groupId = "grp")
    public void listenToOrganizationPost(LeadDto leadDto)
    {
        System.out.println(leadDto);
        kafkaTemplate.send(topic,leadDto);
    }

}
