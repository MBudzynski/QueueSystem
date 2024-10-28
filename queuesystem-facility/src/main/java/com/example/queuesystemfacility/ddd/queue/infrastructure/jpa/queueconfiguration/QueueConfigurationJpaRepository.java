package com.example.queuesystemfacility.ddd.queue.infrastructure.jpa.queueconfiguration;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface QueueConfigurationJpaRepository extends JpaRepository<QueueConfigurationEntity, Long> {
}
