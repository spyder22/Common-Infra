package com.example.supportenginner1.service;

import com.example.supportenginner1.dto.TicketsDto;
import com.example.supportenginner1.payloads.Commit;

import java.util.List;

public interface TicketsService {
    void saveTicket(TicketsDto ticketsDto);
    List<TicketsDto> getAll();
    void addComment(Commit commit);


}
