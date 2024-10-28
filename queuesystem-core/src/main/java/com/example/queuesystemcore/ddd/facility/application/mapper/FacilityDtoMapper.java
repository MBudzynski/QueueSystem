package com.example.queuesystemcore.ddd.facility.application.mapper;

import com.example.queuesystemcore.common.domain.FacilityDto;
import com.example.queuesystemcore.ddd.facility.domain.Facility;

public class FacilityDtoMapper {

    public static FacilityDto valueOf(Facility data) {
        return FacilityDto
                .builder()
                .facilityId(data.getFacilityId())
                .facilityUUID(data.getFacilityUUID())
                .institutionName(data.getInstitutionName())
                .city(data.getCity())
                .street(data.getStreet())
                .houseNumber(data.getHouseNumber())
                .postCode(data.getPostCode())
                .postOffice(data.getPostOffice())
                .pathToLogoFile(data.getPathToLogoFile())
                .queueName(data.getQueueName())
                .build();
    }
}
