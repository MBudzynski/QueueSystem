package com.example.queuesystemcore.ddd.ekiosk.domain;

import java.util.Optional;
import java.util.UUID;

public interface KioskRepository {
    Optional<KioskDto> findKioskData(UUID kioskUUID, Long localizationId);
}
