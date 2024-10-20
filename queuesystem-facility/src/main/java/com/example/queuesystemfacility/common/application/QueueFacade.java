package com.example.queuesystemfacility.common.application;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public interface QueueFacade {

    void deleteQueueNumber(UUID queueUUID);

    void delayQueueNumber(@NotNull UUID queueUUID);
}
