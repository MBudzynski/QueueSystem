package com.example.queuesystemcore.ddd.queue.infrastructure.jpa.queue;

import com.example.queuesystemcore.ddd.queue.domain.QueueDto;
import com.example.queuesystemcore.ddd.queue.domain.QueueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class QueueAdapter implements QueueRepository {

    private final QueueJpaRepository queueJpaRepository;

    @Override
    public void addToQueue(QueueDto toQueue) {
        queueJpaRepository.save(toQueue.toEntity());
    }
}
