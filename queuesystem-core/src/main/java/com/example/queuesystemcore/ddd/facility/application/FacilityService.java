package com.example.queuesystemcore.ddd.facility.application;

import com.example.queuesystemcore.ddd.facility.domain.Facility;
import com.example.queuesystemcore.ddd.facility.domain.FacilityRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@AllArgsConstructor
class FacilityService {

    private final FacilityRepository facilityRepository;

    public Optional<Facility> findFacilityIdByUUID(UUID facilityUUID) {
        return facilityRepository.findFacilityByUUID(facilityUUID);
    }


    public List<String> getAllQueueNames() {
        return facilityRepository.getAllQueueNames();
    }
}
