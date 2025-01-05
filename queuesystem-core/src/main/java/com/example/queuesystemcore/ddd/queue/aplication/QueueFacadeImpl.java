package com.example.queuesystemcore.ddd.queue.aplication;

import com.example.queuesystemcore.common.application.QueueFacade;
import com.example.queuesystemcore.common.domain.QueueConfigurationDto;
import com.example.queuesystemcore.common.domain.QueueNumberDto;
import com.example.queuesystemcore.ddd.queue.domain.QueueConfiguration;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
class QueueFacadeImpl implements QueueFacade {

    private final QueueConfigurationService queueConfigurationService;
    private final QueueService queueService;

     public List<QueueConfigurationDto> getQueuesConfiguredAtFacility(UUID facilityUUID) {
         return queueConfigurationService
                 .getQueueConfigurationsAfFacility(facilityUUID)
                 .stream()
                 .map(QueueConfiguration::toDto)
                 .toList();
     }

    @Override
    public QueueNumberDto queuePetitioner(UUID queueConfigurationUUID, UUID facilityUUID) {
        return queueService.queuePetitioner(queueConfigurationUUID, facilityUUID);
    }
}
