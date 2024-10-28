package com.example.queuesystemfacility.ddd.queue.infrastructure.jpa.queueconfiguration;

import com.example.queuesystemfacility.ddd.queue.domain.QueueConfiguration;
import com.example.queuesystemfacility.ddd.queue.domain.QueueConfigurationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
class QueueConfigurationAdapter implements QueueConfigurationRepository {

    private final QueueConfigurationJpaRepository queueConfigurationJpaRepository;

    @Override
    public List<QueueConfiguration> getAllQueueConfigurations() {
        return queueConfigurationJpaRepository
                .findAll()
                .stream()
                .map(QueueConfigurationEntity::translateTo)
                .toList();
    }

    @Override
    public void deleteQueueConfiguration(Long queueConfigurationId) {
        queueConfigurationJpaRepository.deleteById(queueConfigurationId);
    }

    @Override
    public void saveQueueConfiguration(QueueConfiguration queueConfiguration) {
        QueueConfigurationEntity queueConfigurationEntity = new QueueConfigurationEntity();
        queueConfigurationEntity.mutateTo(queueConfiguration);
        queueConfigurationJpaRepository.save(queueConfigurationEntity);
    }

    @Override
    public void saveAllQueueConfigurations(List<QueueConfiguration> queueConfigurations) {
        List<QueueConfigurationEntity> entities = queueConfigurations
                .stream()
                .map(queueConfiguration -> {
                    QueueConfigurationEntity queueConfigurationEntity = new QueueConfigurationEntity();
                    queueConfigurationEntity.mutateTo(queueConfiguration);
                    return queueConfigurationEntity;
                })
                .toList();

        queueConfigurationJpaRepository.saveAll(entities);
    }
}
