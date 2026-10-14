package com.example.queuesystemcore.ddd.facility.application;

import com.example.queuesystemcore.common.application.FacilityFacade;
import com.example.queuesystemcore.common.domain.FacilityDto;
import com.example.queuesystemcore.ddd.facility.application.mapper.FacilityDtoMapper;
import com.example.queuesystemcore.ddd.facility.exception.FacilityNoExistException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@AllArgsConstructor
public class FacilityFacadeImpl implements FacilityFacade {

    private final FacilityService facilityService;

    @Override
    public FacilityDto findFacilityIdByUUID(UUID facilityUUID) {
        return facilityService
                .findFacilityIdByUUID(facilityUUID)
                .map(FacilityDtoMapper::valueOf)
                .orElseThrow(() ->  new FacilityNoExistException("Facility no exist"));
    }

    @Override
    public List<String> getAllQueueNames() {
        return facilityService.getAllQueueNames();
    }
}
