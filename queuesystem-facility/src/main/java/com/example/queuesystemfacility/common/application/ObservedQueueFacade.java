package com.example.queuesystemfacility.common.application;

import java.util.List;
import java.util.UUID;

public interface ObservedQueueFacade {

    List<UUID> getUserQueueObserved(UUID userUUID);

    void saveUserQueueObserved(UUID userUUID, List<UUID> queueConfigurationUUIDs);
}
