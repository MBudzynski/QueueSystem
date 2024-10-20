package com.example.queuesystemfacility.ddd.queue.infrastructure.jpa.queue;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface QueueJpaRepository extends JpaRepository<QueueEntity, Long> {

    void deleteByQueueUuid(UUID queueUUID);

    Optional<QueueEntity> findByQueueUuid(UUID queueUUID);
}
