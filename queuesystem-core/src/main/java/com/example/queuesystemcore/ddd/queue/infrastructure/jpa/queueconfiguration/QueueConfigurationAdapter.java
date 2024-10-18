package com.example.queuesystemcore.ddd.queue.infrastructure.jpa.queueconfiguration;

import com.example.queuesystemcore.ddd.queue.domain.QueueConfiguration;
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
    public Optional<QueueConfiguration> findQueueConfigurationByUuid(UUID uuid, Long facilityId){
        return Optional
                .ofNullable(repository.findByUUIDAndFacilityId(uuid.toString(), facilityId))
                .map(QueueConfigurationEntity::translate);
    }

    @Override
    public void updateCurrentNumberInQueueConfigurationId(Long queueConfigurationId, Integer currentNumber) {
        repository.updateCurrentNumberById(queueConfigurationId, currentNumber);
    }

    @Override
    public List<QueueConfiguration> getAllQueueConfiguration() {
        return repository
                .findAll()
                .stream()
                .map(QueueConfigurationEntity::translate)
                .toList();
    }

    @Override
    public void save(List<QueueConfiguration> queueConfigurations) {
        repository.saveAll(queueConfigurations
                .stream()
                .map(QueueConfigurationEntity::mutateTo)
                .toList());
    }

    @Override
    public List<QueueConfiguration> findQueueConfigurationByFacilityId(Long facilityId) {
        return repository
                .findByFacilityId(facilityId)
                .stream()
                .map(QueueConfigurationEntity::translate)
                .toList();
    }
}
