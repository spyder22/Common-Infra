package com.example.facebookcrmconfig.service;

import com.example.facebookcrmconfig.dto.LeadDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;


@Service
public class FacebookService {

    public static final String topic="leadfacebook";
    @Autowired
    public KafkaTemplate<String, LeadDto> kafkaTemplate;


    @KafkaListener(topics = "bookk",groupId = "grppp")
    public void listenToOrganizationPost(LeadDto leadDto)
    {
        System.out.println(leadDto);
        kafkaTemplate.send(topic,leadDto);

    }
}
