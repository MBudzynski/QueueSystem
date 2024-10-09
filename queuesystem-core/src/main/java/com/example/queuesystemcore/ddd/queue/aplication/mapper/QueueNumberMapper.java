package com.example.queuesystemcore.ddd.queue.aplication.mapper;

import com.example.queuesystemcore.common.domain.QueueNumberDataDto;
import com.example.queuesystemcore.common.domain.QueueNumberDto;
import com.example.queuesystemcore.ddd.queue.domain.QueueData;
import org.springframework.stereotype.Component;

@Component
public class QueueNumberMapper {

    public QueueNumberDataDto toDto(QueueData data){
        return QueueNumberDataDto.builder()
                .sign(data.getSign())
                .num(data.getNum())
                .fullNumber(data.getFullNumber())
                .queueConfigurationId(data.getQueueConfigurationId())
                .localizationId(data.getLocalizationId())
                .creationTime(data.getCreationTime())
                .creationDate(data.getCreationDate())
                .build();
    }

    public QueueNumberDto toDto(String fullQueueNumber, String queueNumberPdf){
        return new QueueNumberDto(fullQueueNumber, queueNumberPdf);
    }
}
