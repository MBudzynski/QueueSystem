package com.example.queuesystemcore.ddd.queue.aplication;

import com.example.queuesystemcore.common.application.QueueConfigurationFacade;
import com.example.queuesystemcore.common.domain.QueueConfigurationDto;
import com.example.queuesystemcore.ddd.queue.domain.QueueConfiguration;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
class QueueConfigurationFacadeImpl implements QueueConfigurationFacade {

    private final QueueConfigurationService queueConfigurationService;

     public List<QueueConfigurationDto> getQueuesConfiguredAtFacility(UUID facilityUUID) {
         return queueConfigurationService
                 .getQueueConfigurationsAfFacility(facilityUUID)
                 .stream()
                 .map(QueueConfiguration::toDto)
                 .toList();
     }
}
