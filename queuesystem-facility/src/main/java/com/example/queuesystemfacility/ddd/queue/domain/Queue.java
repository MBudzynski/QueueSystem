package com.example.queuesystemfacility.ddd.queue.domain;

import com.example.queuesystemfacility.common.domain.QueueNumberDto;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

@Data
@Builder(toBuilder = true)
public class Queue {

    private Long queueId;
    private UUID queueUUID;
    private String sign;
    private Integer num;
    private String fullNumber;
    private Long queueConfigurationId;
    private Long facilityId;
    private LocalDate creationDate;
    private LocalTime creationTime;
    private Boolean bring;
    private LocalDateTime bringTime;
    private LocalTime delayTime;

    public QueueNumberDto mutateTo(){
        return QueueNumberDto
                .builder()
                .fullNumber(this.fullNumber)
                .queueUUID(this.queueUUID)
                .creationDate(this.creationDate)
                .creationTime(this.creationTime.truncatedTo(ChronoUnit.SECONDS))
                .build();
    }


}
