package com.example.queuesystemcore.ddd.kiosk.infrastructure.jpa.kiosk;

import com.example.queuesystemcore.ddd.kiosk.domain.KioskData;
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
    public Optional<KioskData> findKioskData(UUID kioskUUID, Long localizationId) {
        return repository
                .findKioskByUuidAndLocalization(kioskUUID.toString(), localizationId)
                .map(Kiosk::translate);
    }
}
