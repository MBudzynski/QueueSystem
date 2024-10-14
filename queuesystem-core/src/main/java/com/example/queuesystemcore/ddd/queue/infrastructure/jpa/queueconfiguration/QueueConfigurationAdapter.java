package com.example.queuesystemcore.ddd.queue.infrastructure.jpa.queueconfiguration;

import com.example.queuesystemcore.ddd.queue.domain.QueueConfigurationData;
import com.example.queuesystemcore.ddd.queue.domain.QueueConfigurationRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@AllArgsConstructor
class QueueConfigurationAdapter implements QueueConfigurationRepository {

    private final QueueConfigurationJpaRepository repository;

    @Override
    public Optional<QueueConfigurationData> findQueueConfigurationByUuid(UUID uuid, Long localizationId){
        return Optional
                .ofNullable(repository.findByUUIDAndLocalizationId(uuid.toString(), localizationId))
                .map(QueueConfiguration::translate);
    }

    @Override
    public void updateCurrentNumberInQueueConfigurationId(Long queueConfigurationId, Integer currentNumber) {
        repository.updateCurrentNumberById(queueConfigurationId, currentNumber);
    }

    @Override
    public List<QueueConfigurationData> getAllQueueConfiguration() {
        return repository
                .findAll()
                .stream()
                .map(QueueConfiguration::translate)
                .toList();
    }

    @Override
    public void save(List<QueueConfigurationData> queueConfigurations) {
        repository.saveAll(queueConfigurations
                .stream()
                .map(QueueConfiguration::mutateTo)
                .toList());
    }
}
