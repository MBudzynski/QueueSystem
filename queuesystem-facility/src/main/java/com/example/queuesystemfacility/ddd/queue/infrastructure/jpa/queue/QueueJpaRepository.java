package com.example.queuesystemfacility.ddd.queue.infrastructure.jpa.queue;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface QueueJpaRepository extends JpaRepository<QueueEntity, Long> {

    @Transactional
    void deleteByQueueUuid(UUID queueUUID);

    @Query(value = """
            Update Queue qu
            set bring      = true,
                bring_time = NOW() AT TIME ZONE 'Europe/Warsaw'
            where queue_id = (SELECT q.queue_id
                              FROM Queue q
                              WHERE q.queue_configuration_id in
                                    (SELECT oq.queue_configuration_id FROM observed_queue oq WHERE oq.user_Id = :userId) 
                                AND q.bring IS FALSE
                              ORDER BY COALESCE(q.delay_time, q.creation_time) ASC 
                              LIMIT 1)
            returning *
            """, nativeQuery = true)
    Optional<QueueEntity> findNextNumber(Long userId);

    Optional<QueueEntity> findByQueueUuid(UUID queueUUID);

    @Query(value = """
            SELECT *
            FROM Queue q
            WHERE q.bring IS FALSE
              AND q.queue_configuration_id IN (SELECT oq.queue_configuration_id FROM observed_queue oq WHERE oq.user_id = :userId)
            ORDER BY COALESCE(q.delay_time, q.creation_time) ASC 
            """, nativeQuery = true)
    List<QueueEntity> showAllNumbers(Long userId);
}
