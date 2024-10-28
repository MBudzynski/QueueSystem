package com.example.queuesystemcore.ddd.facility.application;

import com.example.queuesystemcore.common.application.FacilityFacade;
import com.example.queuesystemcore.common.domain.FacilityDto;
import com.example.queuesystemcore.ddd.facility.application.mapper.FacilityDtoMapper;
import com.example.queuesystemcore.ddd.facility.domain.FacilityRepository;
import com.example.queuesystemcore.ddd.facility.exception.FacilityNoExistException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@AllArgsConstructor
class FacilityService implements FacilityFacade {

    private final FacilityRepository facilityRepository;

    @Override
    public FacilityDto findFacilityIdByUUID(UUID facilityUUID) {
        return facilityRepository
                .findFacilityByUUID(facilityUUID)
                .map(FacilityDtoMapper::valueOf)
                .orElseThrow(() ->  new FacilityNoExistException("Facility no exist"));
    }

    @Override
    public List<String> getAllQueueNames() {
        return facilityRepository.getAllQueueNames();
    }
}
