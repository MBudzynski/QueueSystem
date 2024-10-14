package com.example.queuesystemfacility.ddd.queue.domain;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Builder(toBuilder = true)
public class Queue {

    private Long queueId;
    private String sign;
    private Integer num;
    private String fullNumber;
    private Long queueConfigurationId;
    private Long facilityId;
    private LocalDate creationDate;
    private LocalTime creationTime;
}
