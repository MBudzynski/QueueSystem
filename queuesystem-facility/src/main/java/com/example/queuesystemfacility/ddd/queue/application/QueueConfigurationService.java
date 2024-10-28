package com.example.queuesystemfacility.ddd.queue.application;

import com.example.queuesystemfacility.ddd.queue.application.configuration.FacilityConfigurationProvider;
import com.example.queuesystemfacility.ddd.queue.domain.QueueConfiguration;
import com.example.queuesystemfacility.ddd.queue.domain.QueueConfigurationRepository;
import com.example.queuesystemfacility.infrastructure.queue_system_core.QueueSystemCoreClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class QueueConfigurationService {

    private final FacilityConfigurationProvider facilityConfigurationProvider;
    private final QueueConfigurationRepository queueConfigurationRepository;
    private final QueueSystemCoreClient queueSystemCoreClient;

    public void synchronizeQueueConfigurations() {
        List<QueueConfiguration> currentQueueConfigurations = queueConfigurationRepository.getAllQueueConfigurations();
        List<QueueConfiguration> coreQueueConfigurations = queueSystemCoreClient
                .fetchQueueConfigurations(facilityConfigurationProvider.facilityUUID())
                .stream()
                .map(QueueConfiguration::mutateFrom)
                .toList();

        currentQueueConfigurations
                .forEach(current -> {
                    coreQueueConfigurations
                            .stream()
                            .filter(core -> Objects.equals(core.getQueueConfigurationUUID(), current.getQueueConfigurationUUID()))
                            .findFirst()
                            .ifPresentOrElse(core -> {
                                        if (!current.equals(core)) {
                                            queueConfigurationRepository.saveQueueConfiguration(current.update(core));
                                        }
                                    },
                                    () -> queueConfigurationRepository.deleteQueueConfiguration(current.getQueueConfigurationId()));


                });

        List<UUID> currentQueueConfigurationUUID = currentQueueConfigurations
                .stream()
                .map(QueueConfiguration::getQueueConfigurationUUID)
                .toList();

        List<QueueConfiguration> notExistQueueConfigurations = coreQueueConfigurations
                .stream()
                .filter(core -> !currentQueueConfigurationUUID.contains(core.getQueueConfigurationUUID()))
                .toList();

        queueConfigurationRepository.saveAllQueueConfigurations(notExistQueueConfigurations);
    }
}
