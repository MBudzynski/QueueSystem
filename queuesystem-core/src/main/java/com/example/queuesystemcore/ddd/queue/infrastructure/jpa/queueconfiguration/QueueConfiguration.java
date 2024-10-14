package com.example.queuesystemcore.ddd.queue.infrastructure.jpa.queueconfiguration;

import com.example.queuesystemcore.ddd.queue.domain.QueueConfigurationData;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
class QueueConfiguration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long queueConfigurationId;
    @Column(name = "queue_configuration_uuid")
    private String queueConfigurationUUID;
    private String configurationDescription;
    private Long localizationId;
    private Integer currentNumber;
    private Integer numberFrom;
    private Integer numberTo;
    private String sign;

    public QueueConfigurationData translate() {
        return new QueueConfigurationData(
                queueConfigurationId,
                queueConfigurationUUID,
                configurationDescription,
                localizationId,
                currentNumber,
                numberFrom,
                numberTo,
                sign);
    }

    static QueueConfiguration mutateTo(QueueConfigurationData data) {
        return QueueConfiguration.builder()
                .queueConfigurationId(data.getQueueConfigurationId())
                .queueConfigurationUUID(data.getQueueConfigurationUUID())
                .configurationDescription(data.getConfigurationDescription())
                .localizationId(data.getLocalizationId())
                .currentNumber(data.getCurrentNumber())
                .numberFrom(data.getNumberFrom())
                .numberTo(data.getNumberTo())
                .sign(data.getSign())
                .build();
    }
}
