package com.example.queuesystemcore.common.application;

import com.example.queuesystemcore.common.domain.QueueConfigurationDto;

import java.util.List;
import java.util.UUID;

public interface QueueConfigurationFacade {

    List<QueueConfigurationDto> getQueuesConfiguredAtFacility(UUID facilityUUID);
}
