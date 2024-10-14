package com.example.queuesystemfacility.ddd.queue.infrastructure.jpa.queue;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QueueJpaRepository extends JpaRepository<QueueEntity, Long> {
}
