package com.example.queuesystemfacility.ddd.queue.infrastructure.jpa.queueconfiguration;

import com.example.queuesystemfacility.ddd.queue.domain.ObservedQueue;
import com.example.queuesystemfacility.ddd.queue.domain.ObservedQueueRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
class ObservedQueueAdapter implements ObservedQueueRepository {

    private final ObservedQueueJpaRepository observedQueueJpaRepository;

    @Override
    public List<ObservedQueue> getAllObservedQueueByUser(Long userId) {
        return observedQueueJpaRepository
                .findByUserId(userId)
                .stream()
                .map(ObservedQueueEntity::translateTo)
                .toList();
    }

    @Override
    public void deleteAllUserObservedQueue(Long userId) {
        observedQueueJpaRepository.deleteByUserId(userId);
    }

    @Override
    public void saveObservedUserQueues(Long userId, List<UUID> queueConfigurationUUIDs) {
        observedQueueJpaRepository.saveObservedQueue(userId, queueConfigurationUUIDs);
    }
}
