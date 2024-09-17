package com.example.queuesystemcore.ddd.kiosk.application;

import com.example.queuesystemcore.ddd.kiosk.domain.KioskData;
import com.example.queuesystemcore.ddd.kiosk.domain.KioskRepository;
import com.example.queuesystemcore.ddd.kiosk.exception.KioskNoExistException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class KioskProvider {

    private final KioskRepository kioskRepository;

    public KioskData findKioskData(UUID kioskUUID, Long localizationId){
        return kioskRepository.findKioskData(kioskUUID, localizationId)
                .orElseThrow(() -> new KioskNoExistException("Kiosk data no exist"));
    }
}
