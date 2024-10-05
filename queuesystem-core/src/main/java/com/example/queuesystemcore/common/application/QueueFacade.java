package com.example.queuesystemcore.common.application;

import java.util.UUID;

public interface QueueFacade {

    String queuePetitioner(UUID queueConfigurationUUID, UUID localizationUUID);
}
