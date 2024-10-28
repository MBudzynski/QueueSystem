package com.example.queuesystemfacility.ddd.queue.infrastructure.jpa.queue;

import com.example.queuesystemfacility.ddd.queue.domain.Queue;
import com.example.queuesystemfacility.ddd.queue.domain.QueueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.util.List;
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
    public void delayQueueNumber(UUID queueUUID, LocalTime delayTime) {
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

    @Override
    public Queue findNextNumber(Long userId) {
        return queueJpaRepository
                .findNextNumber(userId)
                .map(QueueEntity::translateTo)
                .orElse(null);
    }

    @Override
    public List<Queue> showAllNumbers(Long userId) {
        return queueJpaRepository
                .showAllNumbers(userId)
                .stream()
                .map(QueueEntity::translateTo)
                .toList();
    }
}
