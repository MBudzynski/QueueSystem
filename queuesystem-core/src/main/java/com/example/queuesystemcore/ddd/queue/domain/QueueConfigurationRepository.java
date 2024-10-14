package com.example.queuesystemcore.ddd.queue.domain;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface QueueConfigurationRepository {

    Optional<QueueConfigurationData> findQueueConfigurationByUuid(UUID uuid, Long localizationId);

    void updateCurrentNumberInQueueConfigurationId(Long queueConfigurationId, Integer currentNumber);

    List<QueueConfigurationData> getAllQueueConfiguration();

    void save(List<QueueConfigurationData> queueConfigurations);
}
