package com.example.supportenginner1.repository;

import com.example.supportenginner1.entity.Tickets;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketsRepository extends CrudRepository<Tickets,Long> {
}
