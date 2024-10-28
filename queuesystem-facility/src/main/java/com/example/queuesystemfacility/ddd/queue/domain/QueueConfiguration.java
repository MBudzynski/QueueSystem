package com.example.queuesystemfacility.ddd.queue.domain;

import com.example.queuesystemfacility.common.domain.QueueConfigurationDto;
import lombok.Builder;
import lombok.Getter;

import java.util.Objects;
import java.util.UUID;

@Getter
@Builder
public class QueueConfiguration {

    private Long queueConfigurationId;
    private UUID queueConfigurationUUID;
    private String configurationDescription;
    private String numberRange;
    private String sign;

    public static QueueConfiguration mutateFrom(QueueConfigurationDto queueConfigurationDto) {
        return QueueConfiguration
                .builder()
                .queueConfigurationUUID(queueConfigurationDto.getQueueConfigurationUUID())
                .configurationDescription(queueConfigurationDto.getConfigurationDescription())
                .numberRange(queueConfigurationDto.getNumberRange())
                .sign(queueConfigurationDto.getSign())
                .build();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QueueConfiguration that = (QueueConfiguration) o;
        return Objects.equals(queueConfigurationUUID, that.queueConfigurationUUID) && Objects.equals(configurationDescription, that.configurationDescription) && Objects.equals(numberRange, that.numberRange) && Objects.equals(sign, that.sign);
    }

    @Override
    public int hashCode() {
        return Objects.hash(queueConfigurationUUID, configurationDescription, numberRange, sign);
    }

    public QueueConfiguration update(QueueConfiguration core) {

        if(!this.queueConfigurationUUID.equals(core.getQueueConfigurationUUID())) {
            this.queueConfigurationUUID = core.getQueueConfigurationUUID();
        }

        if(!this.configurationDescription.equals(core.getConfigurationDescription())) {
            this.configurationDescription = core.getConfigurationDescription();
        }

        if(!this.numberRange.equals(core.getNumberRange())) {
            this.numberRange = core.getNumberRange();
        }

        if(!this.sign.equals(core.getSign())) {
            this.sign = core.getSign();
        }

        return this;
    }
}
