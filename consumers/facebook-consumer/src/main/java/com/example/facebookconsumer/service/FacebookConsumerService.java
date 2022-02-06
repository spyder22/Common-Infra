package com.example.facebookconsumer.service;

import com.example.facebookconsumer.dto.OrganizationPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class FacebookConsumerService {

    public static final String topic="org";
    @Autowired
    private KafkaTemplate<String, OrganizationPost> kafkaTemplate;


    @KafkaListener(topics = "toporg",groupId = "group1")
    public void listenToOrganizationPost(OrganizationPost organizationPost)
    {
        System.out.println("----------");
        System.out.println(organizationPost.getUrl());


        kafkaTemplate.send(topic,organizationPost);

    }
}
