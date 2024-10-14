package com.example.queuesystemcore.ddd.queue.aplication.mapper;

import com.example.queuesystemcore.common.domain.QueueDto;
import com.example.queuesystemcore.common.domain.QueueNumberDto;
import org.springframework.stereotype.Component;

@Component
public class QueueNumberMapper {

    public QueueDto toDto(String sign, Integer num, String fullNumber, Long facilityId, Long queueConfigurationId) {
        return QueueDto.valueOf(sign, num, fullNumber, facilityId, queueConfigurationId);
    }

    public QueueNumberDto toDto(String fullQueueNumber, String queueNumberPdf) {
        return new QueueNumberDto(fullQueueNumber, queueNumberPdf);
    }
}
