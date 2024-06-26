package com.example.queuesystemcore.ddd.queue.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class QueueConfigurationDto {
    private Long queueConfigurationId;
    private String UUID;
    private String queueName;
    private String localizationId;
    private Integer currentNumber;
    private Integer numberFrom;
    private Integer numberTo;
    private String sign;

    public Integer getNextNumber() {
        Integer number = currentNumber + 1;
        if(number <= numberTo) {
            return number;
        }

        return numberFrom;
    }

}
