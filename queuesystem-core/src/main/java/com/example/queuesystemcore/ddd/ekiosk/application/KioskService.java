package com.example.queuesystemcore.ddd.ekiosk.application;

import com.example.queuesystemcore.common.application.KioskFacade;
import com.example.queuesystemcore.common.application.LocalizationFacade;
import com.example.queuesystemcore.common.util.FileManagement;
import com.example.queuesystemcore.ddd.ekiosk.domain.KioskDto;
import com.example.queuesystemcore.ddd.ekiosk.ui.rest.response.EKioskConfiguration;
import com.example.queuesystemcore.ddd.localization.domain.LocalizationDto;
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

    public EKioskConfiguration findKioskButtons(UUID kioskUUID, UUID localizationUUID) {

        LocalizationDto localizationData = localizationFacade.findLocalizationIdByUUID(localizationUUID);
        KioskDto kioskData = kioskProvider.findKioskData(kioskUUID, localizationData.getLocationId());
        String institutionLogo;

        try {
            institutionLogo = fileManager.loadFile(localizationData.getPathToLogoFile());
        } catch (FileNotFoundException e) {
            log.error("Error load file: " + localizationData.getPathToLogoFile(), e);
            institutionLogo = "";
        }

        return EKioskConfiguration
                .builder()
                .institutionName(localizationData .getInstitutionName())
                .city(localizationData .getCity())
                .street(localizationData .getStreet())
                .houseNumber(localizationData .getHouseNumber())
                .postCode(localizationData .getPostCode())
                .postOffice(localizationData .getPostOffice())
                .institutionLogoFile(institutionLogo)
                .backgroundColor(kioskData.getBackgroundColor())
                .kioskButtons(kioskData.getKioskButtons())
                .build();
    }
}
