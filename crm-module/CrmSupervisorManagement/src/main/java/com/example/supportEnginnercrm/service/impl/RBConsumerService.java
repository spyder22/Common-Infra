package com.example.supportEnginnercrm.service.impl;

import com.example.supportEnginnercrm.dto.SupportEngineerDto;
import com.example.supportEnginnercrm.entity.SupportEnginner;
import com.example.supportEnginnercrm.payloads.SupervisorTicketDto;
import com.example.supportEnginnercrm.payloads.SupportEngineerTicketDto;
import com.example.supportEnginnercrm.rabbitconfig.RbProducerConfig;
import com.example.supportEnginnercrm.service.impl.SupportEngineerServiceImpl;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Component
public class RBConsumerService {

    @Autowired
    private SupportEngineerServiceImpl supportEngineerServiceImpl;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @RabbitListener(queues = "kabootarja")
    public void supervisorListener(SupervisorTicketDto supervisorTicketDto) {
        System.out.println(supervisorTicketDto);
        //todo : get the supervisor details
        // todo : fetch available support engineers and current count of tickets
        // todo : pick one from the list above
        // todo : if(support engineer queue is not full)
            // todo : assign the ticket to support engineer from the previous list !!
            // todo : put the message in the queue to expiry after 15 minutes to send notification to supervisor
            // todo : develop dead letter queue to consume the expired packets.. if the ticket is not moved .. alert supervisor otherwise ignore
        //todo : else
            // todo : if all support engineers are full to their capacity .. don't assign ticket and put to queue which requires supervisor attention

        List<SupportEngineerDto> response= supportEngineerServiceImpl.findBySupervisorEmailAndAvailableAndTicketCountLessThan(supervisorTicketDto.getSupervisorEmail());

        if(response==null)
        {
            // TODO: 08/02/22 send to supervise attention queue
        }
        else
        {
            List<SupportEngineerDto> available= supportEngineerServiceImpl.findAllBySupervisorEmailOrderByTicketCountAsc(supervisorTicketDto.getSupervisorEmail());

            SupportEngineerDto supportEngineerDto=available.get(0);
            SupportEngineerTicketDto supportEngineerTicketDto=new SupportEngineerTicketDto();
            BeanUtils.copyProperties(supportEngineerDto,supportEngineerTicketDto);

            rabbitTemplate.convertAndSend(RbProducerConfig.EXCHANGE,RbProducerConfig.ROUTING_KEY,supportEngineerTicketDto);

        }


    }
}
