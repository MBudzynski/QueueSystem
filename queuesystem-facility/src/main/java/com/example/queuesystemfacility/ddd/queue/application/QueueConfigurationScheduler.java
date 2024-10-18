package com.example.queuesystemfacility.ddd.queue.application;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@ConditionalOnProperty(name = "custom.schedule.synchronize-queue-configuration.enable")
public class QueueConfigurationScheduler {

    private final QueueConfigurationService queueConfigurationService;

    @Scheduled(cron = "${custom.schedule.synchronize-queue-configuration.cron}")
    public void synchronizeQueueConfiguration() {
        queueConfigurationService.synchronizeQueueConfigurations();
    }
}
