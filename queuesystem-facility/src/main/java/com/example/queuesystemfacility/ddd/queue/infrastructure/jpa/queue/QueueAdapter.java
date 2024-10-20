package com.example.queuesystemfacility.ddd.queue.infrastructure.jpa.queue;

import com.example.queuesystemfacility.ddd.queue.domain.Queue;
import com.example.queuesystemfacility.ddd.queue.domain.QueueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
class QueueAdapter implements QueueRepository {

    private final QueueJpaRepository queueJpaRepository;

    @Override
    public void addToQueue(Queue queue) {
        queueJpaRepository.save(QueueEntity.mutateTo(queue));
    }

    @Override
    public void deleteQueueByUUID(UUID queueUUID) {
        queueJpaRepository.deleteByQueueUuid(queueUUID);
    }

    @Override
    public void delayQueueNumber(UUID queueUUID, LocalDateTime delayTime) {
        Optional<QueueEntity> possibleQueueData = queueJpaRepository.findByQueueUuid(queueUUID);
        possibleQueueData.ifPresentOrElse(queueEntity -> {
                    queueEntity.delayQueueNumber(delayTime);
                    queueJpaRepository.save(queueEntity);
                },
                () -> {
                    throw new IllegalArgumentException("Queue not found");
                }
        );
    }
}
