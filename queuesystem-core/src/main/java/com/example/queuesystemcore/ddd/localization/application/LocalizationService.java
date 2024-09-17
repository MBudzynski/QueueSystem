package com.example.queuesystemcore.ddd.localization.application;

import com.example.queuesystemcore.common.application.LocalizationFacade;
import com.example.queuesystemcore.common.domain.LocalizationDto;
import com.example.queuesystemcore.ddd.localization.application.mapper.LocalizationDtoMapper;
import com.example.queuesystemcore.ddd.localization.domain.LocalizationRepository;
import com.example.queuesystemcore.ddd.localization.exception.LocalizationNoExistException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@AllArgsConstructor
public class LocalizationService implements LocalizationFacade {

    private final LocalizationRepository localizationRepository;

    @Override
    public LocalizationDto findLocalizationIdByUUID(UUID localizationUUID) {
        return localizationRepository
                .findLocalizationByUUID(localizationUUID)
                .map(LocalizationDtoMapper::valueOf)
                .orElseThrow(() ->  new LocalizationNoExistException("localization no exist"));
    }
}
