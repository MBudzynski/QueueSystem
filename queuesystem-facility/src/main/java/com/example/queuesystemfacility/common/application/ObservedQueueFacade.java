package com.example.queuesystemfacility.common.application;

import java.util.List;
import java.util.UUID;

public interface ObservedQueueFacade {

    List<UUID> getObservedUserQueue(UUID userUUID);

    void saveUserObservedQueue(UUID userUUID, List<UUID> queueConfigurationUUIDs);
}
