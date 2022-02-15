package com.example.resourceManagementcrm.service.impl;

import com.example.resourceManagementcrm.payloads.LeadDto;
import com.example.resourceManagementcrm.payloads.SupervisorTicketDto;
import com.example.resourceManagementcrm.rabbitconfig.RbProducerConfig;
import com.example.resourceManagementcrm.service.impl.ResourceManagementServiceImpl;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@EnableRabbit
public class LeadConsumerService {

    @Autowired
    ResourceManagementServiceImpl resourceManagementServiceImpl;

    @Autowired
    RabbitTemplate rabbitTemplate;


    @KafkaListener(topics = {"leadinsta","leadfacebook"},groupId = "groupcrm")
    public void listenToOrganizationPost(LeadDto leadDto)
    {
        System.out.println(leadDto);

        String categoryId = leadDto.getCategoryId();
        String supervisorEmail = getSupervisorForCategory(categoryId);

        //todo : leadDto -> add supervisor details and push to rabbit mq
        SupervisorTicketDto supervisorTicketDto=new SupervisorTicketDto();
        BeanUtils.copyProperties(leadDto,supervisorTicketDto);
        supervisorTicketDto.setSupervisorEmail(supervisorEmail);
        supervisorTicketDto.setLeadMail(leadDto.getUserEmail());

        rabbitTemplate.convertAndSend(RbProducerConfig.EXCHANGE,RbProducerConfig.ROUTING_KEY,supervisorTicketDto);
        System.out.println("messagesent----");

    }

    private String getSupervisorForCategory(String categoryId){
        // todo : load from db, which supervisor is associated with category
        // todo : pick random supervisor from the list, if they have multiple or put in round robin approach here to pick one
        List<String> supervisorEmails=resourceManagementServiceImpl.findAvailableSupervisorByCategory(categoryId);

        return supervisorEmails.get(0);
    }


}
