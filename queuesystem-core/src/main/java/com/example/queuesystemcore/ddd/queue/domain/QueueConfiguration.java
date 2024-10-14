package com.example.queuesystemcore.ddd.queue.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class QueueConfiguration {
    private Long queueConfigurationId;
    private String queueConfigurationUUID;
    private String configurationDescription;
    private Long localizationId;
    private Integer currentNumber;
    private Integer numberFrom;
    private Integer numberTo;
    private String sign;

    public Integer getNextNumber() {
        Integer number = currentNumber + 1;
        if(number <= numberTo) {
            return number;
        }

        restartCurrentNumber();
        return currentNumber + 1;
    }

    public void restartCurrentNumber() {
        this.currentNumber = this.numberFrom - 1;
    }

}
