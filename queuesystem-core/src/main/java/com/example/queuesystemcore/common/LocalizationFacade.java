package com.example.queuesystemcore.common;

import java.util.UUID;

public interface LocalizationFacade {
    Long findLocalizationIdByUUID(UUID localizationUUID);
}
