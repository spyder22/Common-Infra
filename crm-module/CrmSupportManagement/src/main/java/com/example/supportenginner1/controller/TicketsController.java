package com.example.supportenginner1.controller;

import com.example.supportenginner1.dto.TicketsDto;
import com.example.supportenginner1.service.impl.TicketServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/tickets")
@CrossOrigin(value = "*",maxAge = 3600)
public class TicketsController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private TicketServiceImpl ticketServiceImpl;

    @PostMapping("/commit")
    public void commit(@RequestBody TicketsDto ticketsDto)
    {
        ticketServiceImpl.saveTicket(ticketsDto);
        restTemplate.postForEntity("http://localhost:9098/support/decreasecount/"+ticketsDto.getSupportPersonEmail(),HttpMethod.POST, null).getBody();


    }


}
