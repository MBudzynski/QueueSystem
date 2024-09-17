package com.example.queuesystemcore.ddd.localization.application.mapper;

import com.example.queuesystemcore.common.domain.LocalizationDto;
import com.example.queuesystemcore.ddd.localization.domain.LocalizationData;

public class LocalizationDtoMapper {

    public static LocalizationDto valueOf(LocalizationData data) {
        return LocalizationDto
                .builder()
                .locationId(data.getLocationId())
                .locationUUID(data.getLocationUUID())
                .institutionName(data.getInstitutionName())
                .city(data.getCity())
                .street(data.getStreet())
                .houseNumber(data.getHouseNumber())
                .postCode(data.getPostCode())
                .postOffice(data.getPostOffice())
                .pathToLogoFile(data.getPathToLogoFile())
                .build();
    }
}
