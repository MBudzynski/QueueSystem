package com.example.queuesystemcore.common.application;

import com.example.queuesystemcore.common.domain.QueueNumberDto;

import java.util.UUID;

public interface QueueFacade {

    QueueNumberDto queuePetitioner(UUID queueConfigurationUUID, UUID facilityUUID);
}
