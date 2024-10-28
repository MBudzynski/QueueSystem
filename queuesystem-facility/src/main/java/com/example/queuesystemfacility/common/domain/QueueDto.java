package com.example.queuesystemfacility.common.domain;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class QueueDto implements Serializable {

    private String sign;
    private Integer num;
    private String fullNumber;
    private Long facilityId;
    private Long queueConfigurationId;
    private LocalDate creationDate;
    private LocalTime creationTime;
}
