package com.example.crm.repository;

import com.example.crm.entity.Tickets;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketsRepository extends CrudRepository<Tickets,Long> {

}
