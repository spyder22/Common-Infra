package com.example.crm.dto;

import java.util.List;

public class LeadDto {
    private String leadMail;
    private List<Long> ticketIds;

    public String getLeadMail() {
        return leadMail;
    }

    public void setLeadMail(String leadMail) {
        this.leadMail = leadMail;
    }

    public List<Long> getTicketIds() {
        return ticketIds;
    }

    public void setTicketIds(List<Long> ticketIds) {
        this.ticketIds = ticketIds;
    }
}
