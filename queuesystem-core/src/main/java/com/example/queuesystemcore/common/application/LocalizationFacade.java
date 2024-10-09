package com.example.queuesystemcore.common.application;

import com.example.queuesystemcore.common.domain.LocalizationDto;

import java.util.List;
import java.util.UUID;

public interface LocalizationFacade {
    LocalizationDto findLocalizationIdByUUID(UUID localizationUUID);

    List<String> getAllQueueNames();
}
