package com.example.queuesystemcore.ddd.facility.infrastructure.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface FacilityJpaRepository extends JpaRepository<FacilityEntity, Long> {

    FacilityEntity findByFacilityUUID(UUID facilityUUID);

    @Query(value = "SELECT f.queueName FROM FacilityEntity f")
    List<String> getAllQueueNames();
}
