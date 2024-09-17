package com.example.queuesystemcore.ddd.localization.infrastructure.jpa;

import com.example.queuesystemcore.ddd.localization.domain.LocalizationData;
import com.example.queuesystemcore.ddd.localization.domain.LocalizationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class LocalizationAdapter implements LocalizationRepository {

    private final LocalizationJpaRepository repository;

    @Override
    public Optional<LocalizationData> findLocalizationByUUID(UUID localizationUUID) {
        return Optional
                .ofNullable(repository.findByLocationUUID(localizationUUID))
                .map(Localization::translate);
    }
}
