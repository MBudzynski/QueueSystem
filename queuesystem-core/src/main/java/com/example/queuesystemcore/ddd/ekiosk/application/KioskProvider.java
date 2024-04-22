package com.example.queuesystemcore.ddd.ekiosk.application;

import com.example.queuesystemcore.ddd.ekiosk.domain.KioskDto;
import com.example.queuesystemcore.ddd.ekiosk.domain.KioskRepository;
import com.example.queuesystemcore.ddd.ekiosk.exception.KioskNoExistException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class KioskProvider {

    private final KioskRepository kioskRepository;

    public KioskDto findKioskData(UUID kioskUUID, Long localizationId){
        return kioskRepository.findKioskData(kioskUUID, localizationId)
                .orElseThrow(() -> new KioskNoExistException("kiosk data no exist"));
    }
}
