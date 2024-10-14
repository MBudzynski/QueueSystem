package com.example.queuesystemcore.ddd.localization.domain;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface LocalizationRepository {

    Optional<Localization> findLocalizationByUUID(UUID localizationUUID);

    List<String> getAllQueueNames();
}
