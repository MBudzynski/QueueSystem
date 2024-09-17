package com.example.queuesystemcore.ddd.queue.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class QueueConfigurationData {
    private Long queueConfigurationId;
    private String queueConfigurationUUID;
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
