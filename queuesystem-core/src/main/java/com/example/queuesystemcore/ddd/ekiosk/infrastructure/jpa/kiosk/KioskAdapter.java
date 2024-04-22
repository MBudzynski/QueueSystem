package com.example.queuesystemcore.ddd.ekiosk.infrastructure.jpa.kiosk;

import com.example.queuesystemcore.database.entities.Kiosk;
import com.example.queuesystemcore.ddd.ekiosk.domain.KioskDto;
import com.example.queuesystemcore.ddd.ekiosk.domain.KioskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class KioskAdapter implements KioskRepository {

    private final KioskJpaRepository repository;

    @Override
    public Optional<KioskDto> findKioskData(UUID kioskUUID, Long localizationId) {
        return repository
                .findKioskByUuidAndLocalization(kioskUUID.toString(), localizationId)
                .map(Kiosk::translate);
    }
}
