package com.example.queuesystemcore.ddd.localization.infrastructure.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface LocalizationJpaRepository extends JpaRepository<Localization, Long> {

    Localization findByLocationUUID(UUID lcoationUUID);

    @Query(value = "SELECT l.queueName FROM Localization l")
    List<String> getAllQueueNames();
}
