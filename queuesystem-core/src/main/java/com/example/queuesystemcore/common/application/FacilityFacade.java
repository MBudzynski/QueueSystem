package com.example.queuesystemcore.common.application;

import com.example.queuesystemcore.common.domain.FacilityDto;

import java.util.List;
import java.util.UUID;

public interface FacilityFacade {
    FacilityDto findFacilityIdByUUID(UUID facilityUUID);

    List<String> getAllQueueNames();
}
