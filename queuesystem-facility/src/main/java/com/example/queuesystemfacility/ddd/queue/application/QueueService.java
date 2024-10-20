package com.example.queuesystemfacility.ddd.queue.application;

import com.example.queuesystemfacility.common.domain.QueueDto;
import com.example.queuesystemfacility.ddd.queue.application.configuration.FacilityConfigurationProvider;
import com.example.queuesystemfacility.ddd.queue.application.configuration.QueueConfigurationProvider;
import com.example.queuesystemfacility.ddd.queue.application.mapper.QueueMapper;
import com.example.queuesystemfacility.ddd.queue.domain.QueueRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class QueueService {

    private final QueueRepository queueRepository;
    private final QueueMapper queueMapper;
    private final FacilityConfigurationProvider configurationProvider;
    private final QueueConfigurationProvider queueConfigurationProvider;

    @RabbitListener(queues = "#{facilityConfigurationYmlProperties.queueName}")
    public void receiveQueueDtoFormMessageBroker(QueueDto dto) {
        if(!configurationProvider.facilityId().equals(dto.getFacilityId())) {
            log.error("Received queue dto from  wrong facility: {}", dto);
        }

        queueRepository.addToQueue(queueMapper.translateFrom(dto));
    }

    public void deleteQueueNumber(UUID queueUUID) {
        queueRepository.deleteQueueByUUID(queueUUID);
    }

    public void delayQueueNumber(UUID queueUUID) {
        LocalDateTime currentTime = LocalDateTime.now();
        currentTime.plusMinutes(queueConfigurationProvider.delayTimeMinutes());
        queueRepository.delayQueueNumber(queueUUID, currentTime);
    }
}
