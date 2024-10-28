package com.example.queuesystemfacility.ddd.queue.application.mapper;

import com.example.queuesystemfacility.common.domain.QueueDto;
import com.example.queuesystemfacility.ddd.queue.domain.Queue;
import org.springframework.stereotype.Component;

@Component
public class QueueMapper {

    public Queue translateFrom(QueueDto dto) {
        return Queue
                .builder()
                .facilityId(dto.getFacilityId())
                .sign(dto.getSign())
                .num(dto.getNum())
                .fullNumber(String.valueOf(dto.getFullNumber()))
                .queueConfigurationId(dto.getQueueConfigurationId())
                .creationDate(dto.getCreationDate())
                .creationTime(dto.getCreationTime())
                .build();
    }

}
