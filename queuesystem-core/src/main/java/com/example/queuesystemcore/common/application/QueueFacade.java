package com.example.queuesystemcore.common.application;

import com.example.queuesystemcore.common.domain.QueueConfigurationDto;
import com.example.queuesystemcore.common.domain.QueueNumberDto;

import java.util.List;
import java.util.UUID;

public interface QueueFacade {

    List<QueueConfigurationDto> getQueuesConfiguredAtFacility(UUID facilityUUID);

    QueueNumberDto queuePetitioner(UUID queueConfigurationUUID, UUID facilityUUID);
}
