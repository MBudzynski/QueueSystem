package com.example.queuesystemfacility.ddd.queue.application;

import com.example.queuesystemfacility.common.domain.QueueDto;
import com.example.queuesystemfacility.ddd.queue.application.configuration.FacilityConfigurationProvider;
import com.example.queuesystemfacility.ddd.queue.application.configuration.QueueConfigurationProvider;
import com.example.queuesystemfacility.ddd.queue.application.mapper.QueueMapper;
import com.example.queuesystemfacility.ddd.queue.domain.Queue;
import com.example.queuesystemfacility.ddd.queue.domain.QueueRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
class QueueService {

    private final QueueRepository queueRepository;
    private final QueueMapper queueMapper;
    private final FacilityConfigurationProvider configurationProvider;
    private final QueueConfigurationProvider queueConfigurationProvider;

    @RabbitListener(queues = "#{facilityConfigurationYmlProperties.queueName}")
    public void receiveQueueDtoFormMessageBroker(QueueDto dto) {

        if (dto == null) {
            throw new IllegalArgumentException("Received null QueueDto from RabbitMQ");
        }

        if (!configurationProvider.facilityId().equals(dto.getFacilityId())) {
            throw new IllegalArgumentException(
                    "Queue message belongs to a different facility. Expected facilityId="
                            + configurationProvider.facilityId()
                            + ", received=" + dto.getFacilityId());
        }

        try {
            queueRepository.addToQueue(queueMapper.translateFrom(dto));
        } catch (RuntimeException exception) {
            log.error("Failed to process queue message; message will be retried. queueId={}",
                    dto.getFullNumber(), exception);
            throw exception;
        }
    }

    public void deleteQueueNumber(UUID queueUUID) {
        queueRepository.deleteQueueByUUID(queueUUID);
    }

    public void delayQueueNumber(UUID queueUUID) {
        LocalTime currentTime = LocalTime.now();
        currentTime = currentTime.plusMinutes(queueConfigurationProvider.delayTimeMinutes());
        queueRepository.delayQueueNumber(queueUUID, currentTime);
    }

    public Queue bringNextNumber(Long userId) {
        return queueRepository.findNextNumber(userId);
    }

    public List<Queue> showAllNumbers(Long userId) {
       return queueRepository.showAllNumbers(userId);
    }
}
