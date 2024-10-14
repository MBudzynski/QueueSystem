package com.example.queuesystemcore.ddd.queue.infrastructure.jpa.queue;

import com.example.queuesystemcore.ddd.queue.domain.QueueData;
import com.example.queuesystemcore.ddd.queue.domain.QueueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class QueueAdapter implements QueueRepository {

    private final QueueJpaRepository queueJpaRepository;

    @Override
    public void addToQueue(QueueData queueData) {
        queueJpaRepository.save(Queue.mutateTo(queueData));
    }
}
