package com.example.queuesystemfacility.ddd.queue.infrastructure.jpa.queueconfiguration;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ObservedQueueJpaRepository extends JpaRepository<ObservedQueueEntity, Long> {

    List<ObservedQueueEntity> findByUserId(Long userId);

    void deleteByUserId(Long userId);

    @Query(value = """
            INSERT INTO observed_queue (user_Id, queue_configuration_id)
            SELECT :userId, queue_configuration_id 
            FROM queue_configuration 
            WHERE queue_configurationuuid IN :queueConfigurationUUIDs
            returning 1
            """, nativeQuery = true)
    void saveObservedQueue(Long userId, List<UUID> queueConfigurationUUIDs);
}
