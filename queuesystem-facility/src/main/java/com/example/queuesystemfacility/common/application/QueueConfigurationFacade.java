package com.example.queuesystemfacility.common.application;

import com.example.queuesystemfacility.common.domain.QueueConfigurationDto;

import java.util.List;

public interface QueueConfigurationFacade {

    List<QueueConfigurationDto> getConfiguredQueues();
}
