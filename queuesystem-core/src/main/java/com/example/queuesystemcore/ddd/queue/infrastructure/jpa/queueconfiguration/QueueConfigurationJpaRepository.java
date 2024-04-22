package com.example.queuesystemcore.ddd.queue.infrastructure.jpa.queueconfiguration;

import com.example.queuesystemcore.database.entities.QueueConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
interface QueueConfigurationJpaRepository extends JpaRepository<QueueConfiguration, Long> {

    @Query(value = "SELECT * FROM queue_configuration WHERE uuid like :uuid AND localization_Id = :localizationId",nativeQuery = true)
    QueueConfiguration findByUUIDAndLocalizationId(String uuid, Long localizationId);

    @Query(value = "UPDATE queue_configuration SET current_number = :currentNumber WHERE queue_configuration_id = :queryConfigurationId",nativeQuery = true)
    void updateCurrentNumberById(Long queryConfigurationId, Integer currentNumber);
}
