package com.example.queuesystemfacility.ddd.queue.domain;

import java.util.List;

public interface QueueConfigurationRepository {

    List<QueueConfiguration> getAllQueueConfigurations();

    void deleteQueueConfiguration(Long queueConfigurationId);

    void saveQueueConfiguration(QueueConfiguration queueConfiguration);

    void saveAllQueueConfigurations(List<QueueConfiguration> queueConfigurations);
}
