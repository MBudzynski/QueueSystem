package com.example.queuesystemfacility.ddd.queue.infrastructure.jpa.queue;

import com.example.queuesystemfacility.ddd.queue.domain.Queue;
import com.example.queuesystemfacility.ddd.queue.domain.QueueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class QueueAdapter implements QueueRepository {

    private final QueueJpaRepository queueJpaRepository;

    @Override
    public void addToQueue(Queue queue) {
        queueJpaRepository.save(QueueEntity.mutateTo(queue));
    }
}
