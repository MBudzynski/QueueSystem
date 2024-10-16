package com.example.queuesystemcore.ddd.facility.domain;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface FacilityRepository {

    Optional<Facility> findFacilityByUUID(UUID facilityUUID);

    List<String> getAllQueueNames();
}
