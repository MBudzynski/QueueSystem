package com.example.queuesystemcore.ddd.queue.infrastructure.jpa.queue;

import com.example.queuesystemcore.database.entities.Queue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QueueJpaRepository extends JpaRepository<Queue, Long> {
}
