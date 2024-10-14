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
public class QueueDto implements Serializable {

    private String sign;
    private Integer num;
    private String fullNumber;
    private Long facilityId;
    private Long queueConfigurationId;
    private LocalDate creationDate;
    private LocalTime creationTime;

    public static QueueDto valueOf(String sign, Integer num, String fullNumber, Long facilityId, Long queueConfigurationId) {
        return QueueDto.builder()
                .sign(sign)
                .num(num)
                .fullNumber(fullNumber)
                .facilityId(facilityId)
                .queueConfigurationId(queueConfigurationId)
                .creationDate(LocalDate.now())
                .creationTime(LocalTime.now())
                .build();
    }
}
