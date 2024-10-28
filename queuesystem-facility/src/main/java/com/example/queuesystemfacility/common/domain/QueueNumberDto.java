package com.example.queuesystemfacility.common.domain;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Getter
@Builder
public class QueueNumberDto {

    private UUID queueUUID;
    private String fullNumber;
    private LocalDate creationDate;
    private LocalTime creationTime;

}
