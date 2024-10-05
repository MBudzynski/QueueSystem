package com.example.queuesystemcore.common.application;

import com.example.queuesystemcore.ddd.kiosk.ui.rest.response.KioskConfiguration;

import java.util.UUID;

public interface KioskFacade {

    KioskConfiguration findKioskButtons(UUID kioskUUID, UUID localizationUUID);
}
