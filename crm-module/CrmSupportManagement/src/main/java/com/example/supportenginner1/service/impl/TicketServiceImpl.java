package com.example.supportenginner1.service.impl;

import com.example.supportenginner1.dto.TicketsDto;
import com.example.supportenginner1.entity.Bucket;
import com.example.supportenginner1.entity.Tickets;
import com.example.supportenginner1.payloads.Commit;
import com.example.supportenginner1.repository.BucketRepository;
import com.example.supportenginner1.repository.TicketsRepository;
import com.example.supportenginner1.service.TicketsService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TicketServiceImpl implements TicketsService {

    @Autowired
    private TicketsRepository ticketsRepository;


    @Autowired
    private BucketRepository bucketRepository;

    @Override
    @Transactional
    public void saveTicket(TicketsDto ticketsDto) {

        Tickets tickets=new Tickets();
        Bucket bucket=new Bucket();

        BeanUtils.copyProperties(ticketsDto,tickets);
        BeanUtils.copyProperties(ticketsDto,bucket);

        Long datetime = System.currentTimeMillis();
        Timestamp timestamp = new Timestamp(datetime);
        tickets.setCreatedTime(timestamp);
        try
        {

            bucketRepository.save(bucket);
            ticketsRepository.save(tickets);
        }
        catch (Exception e)
        {
            System.out.println("Exception while saving tickets");
            e.printStackTrace();
        }
    }

    @Override
    public List<TicketsDto> getAll() {
        List<Tickets> response=(List)ticketsRepository.findAll();
        List<TicketsDto> ticketsDtoList=new ArrayList<>();
        for(Tickets tickets:response)
        {
            TicketsDto ticketsDto=new TicketsDto();
            BeanUtils.copyProperties(tickets,ticketsDto);
            ticketsDtoList.add(ticketsDto);
        }
        return ticketsDtoList;
    }



    @Override
    @Transactional
    public void addComment(Commit commit) {
        Optional<Tickets> response=ticketsRepository.findById(commit.getTicketId());
        if(response.isPresent())
        {
            Tickets tickets=response.get();
            Long datetime = System.currentTimeMillis();
            Timestamp timestamp = new Timestamp(datetime);

            tickets.setComment(commit.getComment());
            tickets.setEndedTime(timestamp);
            try
            {
                bucketRepository.deleteById(commit.getBucketId());
                ticketsRepository.save(tickets);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }

        }
    }
}

















//    @Override
//    public void addCreatedTime(Long ticketId) {
//        Long datetime = System.currentTimeMillis();
//        Timestamp timestamp = new Timestamp(datetime);
//        Optional<Tickets> response=ticketsRepository.findById(ticketId);
//        if(response.isPresent())
//        {
//            Tickets tickets=response.get();
//            tickets.setCreatedTime(timestamp);
//            ticketsRepository.save(tickets);
//        }
//    }
//
//    @Override
//    public void addEndedTime(Long ticketId) {
//        Long datetime = System.currentTimeMillis();
//        Timestamp timestamp = new Timestamp(datetime);
//        Optional<Tickets> response=ticketsRepository.findById(ticketId);
//        if(response.isPresent())
//        {
//            Tickets tickets=response.get();
//            tickets.setEndedTime(timestamp);
//            ticketsRepository.save(tickets);
//        }
//    }