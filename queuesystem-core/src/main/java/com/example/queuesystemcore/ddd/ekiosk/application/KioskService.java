package com.example.queuesystemcore.ddd.ekiosk.application;

import com.example.queuesystemcore.common.KioskFacade;
import com.example.queuesystemcore.common.LocalizationFacade;
import com.example.queuesystemcore.ddd.ekiosk.domain.KioskDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class KioskService implements KioskFacade {

    private final LocalizationFacade localizationFacade;
    private final KioskProvider kioskProvider;

    public KioskDto findKioskButtons(UUID kioskUUID, UUID localizationUUID) {
        Long localizationId = localizationFacade.findLocalizationIdByUUID(localizationUUID);
        return kioskProvider.findKioskData(kioskUUID, localizationId);
    }
}
