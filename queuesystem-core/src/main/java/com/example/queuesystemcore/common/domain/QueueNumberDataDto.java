package com.example.queuesystemcore.common.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QueueNumberDataDto implements Serializable {

    private Long queueId;
    private String sign;
    private Integer num;
    private String fullNumber;
    private Long queueConfigurationId;
    private Long localizationId;
    private LocalDate creationDate;
    private LocalTime creationTime;
}
