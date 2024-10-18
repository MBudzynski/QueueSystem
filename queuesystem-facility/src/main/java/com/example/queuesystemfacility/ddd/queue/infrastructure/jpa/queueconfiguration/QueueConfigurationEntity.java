package com.example.queuesystemfacility.ddd.queue.infrastructure.jpa.queueconfiguration;

import com.example.queuesystemfacility.ddd.queue.domain.QueueConfiguration;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@NoArgsConstructor
class QueueConfigurationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long queueConfigurationId;
    private UUID queueConfigurationUUID;
    private String configurationDescription;
    private String numberRange;
    private String sign;

    public QueueConfiguration translateTo() {
        return QueueConfiguration
                .builder()
                .queueConfigurationId(queueConfigurationId)
                .queueConfigurationUUID(queueConfigurationUUID)
                .configurationDescription(configurationDescription)
                .numberRange(numberRange)
                .sign(sign)
                .build();
    }

    public void mutateTo(QueueConfiguration queueConfiguration) {
        this.queueConfigurationId = queueConfiguration.getQueueConfigurationId();
        this.queueConfigurationUUID = queueConfiguration.getQueueConfigurationUUID();
        this.configurationDescription = queueConfiguration.getConfigurationDescription();
        this.numberRange = queueConfiguration.getNumberRange();
        this.sign = queueConfiguration.getSign();
    }
}
