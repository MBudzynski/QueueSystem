package com.example.queuesystemfacility.ddd.queue.domain;

import java.util.List;
import java.util.UUID;

public interface ObservedQueueRepository {

    List<ObservedQueue> getAllObservedQueueByUser(Long userId);

    void deleteAllUserObservedQueue(Long userId);

    void saveObservedUserQueues(Long userId, List<UUID> queueConfigurationUUIDs);
}
