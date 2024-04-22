package com.example.queuesystemcore.ddd.localization.domain;

import java.util.Optional;
import java.util.UUID;

public interface LocalizationRepository {
    Optional<LocalizationDto> findLocalizationByUUID(UUID localizationUUID);
}
