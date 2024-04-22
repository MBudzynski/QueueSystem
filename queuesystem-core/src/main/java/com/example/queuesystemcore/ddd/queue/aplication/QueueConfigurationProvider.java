package com.example.queuesystemcore.ddd.queue.aplication;

import com.example.queuesystemcore.ddd.queue.domain.QueueConfigurationDto;
import com.example.queuesystemcore.ddd.queue.domain.QueueConfigurationRepository;
import com.example.queuesystemcore.ddd.queue.exceprion.QueueConfigurationNoExistException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class QueueConfigurationProvider {

    private final QueueConfigurationRepository queueConfigurationRepository;

    public QueueConfigurationDto findQueueConfigurationByUUID(UUID uuid, Long localizationId){
        return queueConfigurationRepository
                .findQueueConfigurationByUuid(uuid, localizationId)
                .orElseThrow(() -> new QueueConfigurationNoExistException("queue configuration no exist"));
    }

    public void updateCurrentNumber(Long queueConfigurationId, Integer currentNumber) {
        queueConfigurationRepository.updateCurrentNumberInQueueConfigurationId(queueConfigurationId, currentNumber);
    }
}
