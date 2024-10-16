package com.example.queuesystemcore.ddd.queue.infrastructure.jpa.queueconfiguration;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
interface QueueConfigurationJpaRepository extends JpaRepository<QueueConfigurationEntity, Long> {

    @Query(value = "SELECT * FROM queue_configuration WHERE queue_configuration_uuid like :uuid AND facility_id = :facilityId",nativeQuery = true)
    QueueConfigurationEntity findByUUIDAndFacilityId(String uuid, Long facilityId);

    @Modifying
    @Query(value = "UPDATE queue_configuration SET current_number = :currentNumber WHERE queue_configuration_id = :queryConfigurationId",nativeQuery = true)
    void updateCurrentNumberById(Long queryConfigurationId, Integer currentNumber);
}
