package com.example.queuesystemcore.ddd.queue.infrastructure.jpa.queueconfiguration;

import com.example.queuesystemcore.ddd.queue.domain.QueueConfigurationData;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public class QueueConfiguration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long queueConfigurationId;
    private String queueConfigurationUUID;
    private String queueName;
    private String localizationId;
    private Integer currentNumber;
    private Integer numberFrom;
    private Integer numberTo;
    private String sign;

    public QueueConfigurationData translate() {
        return new QueueConfigurationData(
                queueConfigurationId,
                queueConfigurationUUID,
                queueName,
                localizationId,
                currentNumber,
                numberFrom,
                numberTo,
                sign);
    }
}
