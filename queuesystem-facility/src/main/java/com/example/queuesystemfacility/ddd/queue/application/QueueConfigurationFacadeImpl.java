package com.example.queuesystemfacility.ddd.queue.application;

import com.example.queuesystemfacility.common.application.QueueConfigurationFacade;
import com.example.queuesystemfacility.common.domain.QueueConfigurationDto;
import com.example.queuesystemfacility.ddd.queue.domain.QueueConfiguration;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class QueueConfigurationFacadeImpl implements QueueConfigurationFacade {

    private final QueueConfigurationService queueConfigurationService;

    @Override
    public List<QueueConfigurationDto> getConfiguredQueues() {
        return queueConfigurationService
                .getAllConfiguredQueues()
                .stream()
                .map(QueueConfiguration::toDto)
                .toList();
    }
}
