package com.example.queuesystemcore.ddd.queue.domain;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface QueueConfigurationRepository {

    Optional<QueueConfiguration> findQueueConfigurationByUuid(UUID uuid, Long facilityId);

    void updateCurrentNumberInQueueConfigurationId(Long queueConfigurationId, Integer currentNumber);

    List<QueueConfiguration> getAllQueueConfiguration();

    void save(List<QueueConfiguration> queueConfigurations);
}
