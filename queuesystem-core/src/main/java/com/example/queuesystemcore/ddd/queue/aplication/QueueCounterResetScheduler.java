package com.example.queuesystemcore.ddd.queue.aplication;

import com.example.queuesystemcore.ddd.queue.domain.QueueConfiguration;
import com.example.queuesystemcore.ddd.queue.domain.QueueConfigurationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@ConditionalOnProperty(value = "${custom.schedule.queue-configuration.enable}")
class QueueCounterResetScheduler {

    private final QueueConfigurationRepository queueConfigurationRepository;

    @Scheduled(cron = "${custom.schedule.queue-configuration.cron}")
    public void resetCurrenNumberAtQueueConfigurations() {
        List<QueueConfiguration> allQueueConfigurations = queueConfigurationRepository.getAllQueueConfiguration();
        allQueueConfigurations.forEach(QueueConfiguration::restartCurrentNumber);
        queueConfigurationRepository.save(allQueueConfigurations);
    }
}
