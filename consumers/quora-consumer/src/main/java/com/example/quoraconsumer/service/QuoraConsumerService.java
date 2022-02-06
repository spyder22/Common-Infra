package com.example.quoraconsumer.service;

import com.example.quoraconsumer.dto.OrganizationPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;

public class QuoraConsumerService {

    @Autowired
    private KafkaTemplate<String, OrganizationPost> kafkaTemplate;

    public static final String topic="organization_posts3";

    @KafkaListener(topics = "c",groupId = "group3")
    public void listenToOrganizationPost(OrganizationPost organizationPost)
    {
        System.out.println(organizationPost.getUrl());


        kafkaTemplate.send(topic,organizationPost);

    }
}
