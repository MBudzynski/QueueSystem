package com.example.queuesystemfacility.infrastructure.queue_system_core;

import com.example.queuesystemfacility.common.domain.QueueConfigurationDto;

import java.util.List;
import java.util.UUID;

public interface QueueSystemCoreClient {

    List<QueueConfigurationDto> fetchQueueConfigurations(UUID facilityUUID);
}
