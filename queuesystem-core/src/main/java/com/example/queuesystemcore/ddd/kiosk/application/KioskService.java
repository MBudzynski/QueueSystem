package com.example.queuesystemcore.ddd.kiosk.application;

import com.example.queuesystemcore.common.application.KioskFacade;
import com.example.queuesystemcore.common.application.LocalizationFacade;
import com.example.queuesystemcore.common.domain.LocalizationDto;
import com.example.queuesystemcore.common.util.FileManagement;
import com.example.queuesystemcore.ddd.kiosk.domain.KioskData;
import com.example.queuesystemcore.ddd.kiosk.ui.rest.response.KioskConfiguration;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class KioskService implements KioskFacade {

    private final LocalizationFacade localizationFacade;
    private final KioskProvider kioskProvider;
    private final FileManagement fileManager;

    public KioskConfiguration findKioskButtons(UUID kioskUUID, UUID localizationUUID) {

        LocalizationDto localizationDto = localizationFacade.findLocalizationIdByUUID(localizationUUID);
        KioskData kioskData = kioskProvider.findKioskData(kioskUUID, localizationDto.getLocationId());
        String institutionLogo;

        try {
            institutionLogo = fileManager.loadFile(localizationDto.getPathToLogoFile());
        } catch (FileNotFoundException e) {
            log.error("Error load file: " + localizationDto.getPathToLogoFile(), e);
            institutionLogo = "";
        }

        return KioskConfiguration
                .builder()
                .institutionName(localizationDto.getInstitutionName())
                .city(localizationDto.getCity())
                .street(localizationDto.getStreet())
                .houseNumber(localizationDto.getHouseNumber())
                .postCode(localizationDto.getPostCode())
                .postOffice(localizationDto.getPostOffice())
                .institutionLogoFile(institutionLogo)
                .backgroundColor(kioskData.getBackgroundColor())
                .kioskButtons(kioskData.getKioskButtons())
                .build();
    }
}
