package com.example.queuesystemcore.ddd.kiosk.application;

import com.example.queuesystemcore.common.application.KioskFacade;
import com.example.queuesystemcore.common.application.FacilityFacade;
import com.example.queuesystemcore.common.domain.FacilityDto;
import com.example.queuesystemcore.common.util.FileManagement;
import com.example.queuesystemcore.ddd.kiosk.domain.Kiosk;
import com.example.queuesystemcore.ddd.kiosk.ui.rest.response.KioskConfiguration;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
class KioskService implements KioskFacade {

    private final FacilityFacade facilityFacade;
    private final KioskProvider kioskProvider;
    private final FileManagement fileManager;

    public KioskConfiguration findKioskButtons(UUID kioskUUID, UUID facilityUUID) {

        FacilityDto facilityDto = facilityFacade.findFacilityIdByUUID(facilityUUID);
        Kiosk kiosk = kioskProvider.findKioskData(kioskUUID, facilityDto.getFacilityId());
        String institutionLogo;

        try {
            institutionLogo = fileManager.loadFile(facilityDto.getPathToLogoFile());
        } catch (FileNotFoundException e) {
            log.error("Error load file: " + facilityDto.getPathToLogoFile(), e);
            institutionLogo = "";
        }

        return KioskConfiguration
                .builder()
                .institutionName(facilityDto.getInstitutionName())
                .city(facilityDto.getCity())
                .street(facilityDto.getStreet())
                .houseNumber(facilityDto.getHouseNumber())
                .postCode(facilityDto.getPostCode())
                .postOffice(facilityDto.getPostOffice())
                .institutionLogoFile(institutionLogo)
                .backgroundColor(kiosk.getBackgroundColor())
                .kioskButtons(kiosk.getKioskButtons())
                .build();
    }
}
