package com.example.queuesystemcore.ddd.queue.domain;

import com.example.queuesystemcore.common.domain.QueueConfigurationDto;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class QueueConfiguration {
    private Long queueConfigurationId;
    private String queueConfigurationUUID;
    private String configurationDescription;
    private Long facilityId;
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

    public QueueConfigurationDto toDto() {
        return QueueConfigurationDto.builder()
                .queueConfigurationUUID(UUID.fromString(this.queueConfigurationUUID))
                .configurationDescription(this.configurationDescription)
                .numberRange(this.numberFrom.toString() + " - " + this.numberTo.toString())
                .sign(this.sign)
                .build();
    }

}
