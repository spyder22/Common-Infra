package com.example.instaconsumer.service;

import com.example.instaconsumer.dto.OrganizationPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class InstaConsumerService {
    @Autowired
    private KafkaTemplate<String, OrganizationPost> kafkaTemplate;

    public static final String topic="orgg";


    @KafkaListener(topics = "topp",groupId = "group2")
    public void listenToOrganizationPost(OrganizationPost organizationPost)
    {
        System.out.println(organizationPost.getUrl());


        kafkaTemplate.send(topic,organizationPost);

    }
}
