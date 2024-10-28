package com.example.queuesystemcore.ddd.kiosk.infrastructure.jpa.kiosk;

import com.example.queuesystemcore.ddd.kiosk.domain.Kiosk;
import com.example.queuesystemcore.ddd.kiosk.domain.KioskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
class KioskAdapter implements KioskRepository {

    private final KioskJpaRepository repository;

    @Override
    public Optional<Kiosk> findKioskData(UUID kioskUUID, Long facilityId) {
        return repository
                .findKioskByUuidAndFacility(kioskUUID.toString(), facilityId)
                .map(KioskEntity::translate);
    }
}
