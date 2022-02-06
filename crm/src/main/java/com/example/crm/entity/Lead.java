package com.example.crm.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;

@Table
@Entity
public class Lead {

    @Id
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
