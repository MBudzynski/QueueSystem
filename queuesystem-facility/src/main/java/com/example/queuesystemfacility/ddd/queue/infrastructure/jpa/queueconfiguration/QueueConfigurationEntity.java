package com.example.queuesystemfacility.ddd.queue.infrastructure.jpa.queueconfiguration;

import com.example.queuesystemfacility.ddd.queue.domain.QueueConfiguration;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@NoArgsConstructor
@Table(name = "queue_configuration")
class QueueConfigurationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long queueConfigurationId;
    private UUID queueConfigurationUUID;
    private String configurationDescription;
    private String numberRange;
    private String sign;

    @OneToMany(mappedBy = "queueConfiguration", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ObservedQueueEntity> observedQueues = new HashSet<>();

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
