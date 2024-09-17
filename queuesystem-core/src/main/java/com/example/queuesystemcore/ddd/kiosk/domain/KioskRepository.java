package com.example.queuesystemcore.ddd.kiosk.domain;

import java.util.Optional;
import java.util.UUID;

public interface KioskRepository {
    Optional<KioskData> findKioskData(UUID kioskUUID, Long localizationId);
}
