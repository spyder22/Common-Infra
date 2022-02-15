package com.example.supportEnginnercrm.repository;

import com.example.supportEnginnercrm.entity.SupportEnginner;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SupportEnginnerRepository extends CrudRepository<SupportEnginner,String > {

//    @Query("select * from support_engineer where supervisor_email=?1 and ticket_count<?2")
    List<SupportEnginner> findAllBySupervisorEmailAndTicketCountLessThan(String supervisorEmail,int lessThan);
    List<SupportEnginner> findAllBySupervisorEmail(String supervisorEmail);
    List<SupportEnginner> findAllBySupervisorEmailAndAvailable(String supervisorEmail,Boolean available);
    List<SupportEnginner> findAllBySupervisorEmailAndAvailableAndTicketCountLessThan(String supervisorEmail,Boolean available,int ticketCount);

    List<SupportEnginner> findAllBySupervisorEmailOrderByTicketCountAsc(String supervisorEmail);





}
