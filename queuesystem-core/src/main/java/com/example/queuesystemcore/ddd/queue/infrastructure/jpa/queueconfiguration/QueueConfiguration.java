package com.example.queuesystemcore.ddd.queue.infrastructure.jpa.queueconfiguration;

import com.example.queuesystemcore.ddd.queue.domain.QueueConfigurationData;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public class QueueConfiguration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long queueConfigurationId;
    @Column(name = "queue_configuration_uuid")
    private String queueConfigurationUUID;
    private String queueName;
    private Long localizationId;
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
