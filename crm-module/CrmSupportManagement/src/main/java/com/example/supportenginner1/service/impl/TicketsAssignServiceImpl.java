package com.example.supportenginner1.service.impl;

import com.example.supportenginner1.dto.TicketsDto;
import com.example.supportenginner1.payloads.SupportEngineerTicketDto;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
public class TicketsAssignServiceImpl {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private TicketServiceImpl ticketServiceImpl;



    @RabbitListener(queues = "kabootaraagya")
    public void supervisorListener(SupportEngineerTicketDto supportEngineerTicketDto) {

// TODO: 08/02/22 increase the ticket count of support engineer

        TicketsDto ticketsDto=new TicketsDto();
        BeanUtils.copyProperties(supportEngineerTicketDto,ticketsDto);
        ticketServiceImpl.saveTicket(ticketsDto);

    }



}
