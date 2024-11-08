package com.example.queuesystemfacility.ddd.queue.application;

import com.example.queuesystemfacility.ddd.queue.domain.ObservedQueue;
import com.example.queuesystemfacility.ddd.queue.domain.ObservedQueueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
class ObservedUserQueueService {

    private final ObservedQueueRepository observedQueueRepository;

    public List<ObservedQueue> getObservedUserQueue(Long userId) {
       return observedQueueRepository.getAllObservedQueueByUser(userId);
    }

    @Transactional
    public void saveUserQueueObserved(Long userId, List<UUID> queueConfigurationUUIDs) {
        observedQueueRepository.deleteAllUserObservedQueue(userId);
        observedQueueRepository.saveUserQueueObserved(userId, queueConfigurationUUIDs);
    }
}
