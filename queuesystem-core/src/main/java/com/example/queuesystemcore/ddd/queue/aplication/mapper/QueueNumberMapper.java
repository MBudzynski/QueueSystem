package com.example.queuesystemcore.ddd.queue.aplication.mapper;

import com.example.queuesystemcore.common.domain.QueueNumberDataDto;
import com.example.queuesystemcore.common.domain.QueueNumberDto;
import org.springframework.stereotype.Component;

@Component
public class QueueNumberMapper {

    public QueueNumberDataDto toDto(String sign, Integer num, String fullNumber, Long queueConfigurationId) {
        return QueueNumberDataDto.valueOf(sign, num, fullNumber, queueConfigurationId);
    }

    public QueueNumberDto toDto(String fullQueueNumber, String queueNumberPdf) {
        return new QueueNumberDto(fullQueueNumber, queueNumberPdf);
    }
}
