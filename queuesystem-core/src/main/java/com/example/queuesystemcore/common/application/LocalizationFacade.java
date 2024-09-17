package com.example.queuesystemcore.common.application;

import com.example.queuesystemcore.ddd.localization.domain.LocalizationDto;

import java.util.UUID;

public interface LocalizationFacade {
    LocalizationDto findLocalizationIdByUUID(UUID localizationUUID);
}
