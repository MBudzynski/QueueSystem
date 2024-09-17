package com.example.queuesystemcore.ddd.queue.infrastructure.jpa.queueconfiguration;

import com.example.queuesystemcore.ddd.queue.domain.QueueConfigurationData;
import com.example.queuesystemcore.ddd.queue.domain.QueueConfigurationRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@AllArgsConstructor
class QueueConfigurationAdapter implements QueueConfigurationRepository {

    private final QueueConfigurationJpaRepository repository;

    public Optional<QueueConfigurationData> findQueueConfigurationByUuid(UUID uuid, Long localizationId){
        return Optional
                .ofNullable(repository.findByUUIDAndLocalizationId(uuid.toString(), localizationId))
                .map(QueueConfiguration::translate);
    }

    @Override
    public void updateCurrentNumberInQueueConfigurationId(Long queueConfigurationId, Integer currentNumber) {
        repository.updateCurrentNumberById(queueConfigurationId, currentNumber);
    }
}
