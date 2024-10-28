package com.example.queuesystemcore.ddd.queue.aplication;

import com.example.queuesystemcore.ddd.queue.domain.QueueConfiguration;
import com.example.queuesystemcore.ddd.queue.domain.QueueConfigurationRepository;
import com.example.queuesystemcore.ddd.queue.exceprion.QueueConfigurationNoExistException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
class QueueConfigurationProvider {

    private final QueueConfigurationRepository queueConfigurationRepository;

    public QueueConfiguration findQueueConfigurationByUUID(UUID uuid, Long facilityId){
        return queueConfigurationRepository
                .findQueueConfigurationByUuid(uuid, facilityId)
                .orElseThrow(() -> new QueueConfigurationNoExistException("queue configuration no exist"));
    }

    public void updateCurrentNumber(Long queueConfigurationId, Integer currentNumber) {
        queueConfigurationRepository.updateCurrentNumberInQueueConfigurationId(queueConfigurationId, currentNumber);
    }

    public List<QueueConfiguration> findQueueConfigurationByFacilityUUID(Long facilityId) {
        return queueConfigurationRepository.findQueueConfigurationByFacilityId(facilityId);
    }
}
