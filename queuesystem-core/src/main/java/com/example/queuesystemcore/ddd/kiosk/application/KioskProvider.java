package com.example.queuesystemcore.ddd.kiosk.application;

import com.example.queuesystemcore.ddd.kiosk.domain.Kiosk;
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

    public Kiosk findKioskData(UUID kioskUUID, Long facilityId){
        return kioskRepository.findKioskData(kioskUUID, facilityId)
                .orElseThrow(() -> new KioskNoExistException("KioskEntity data no exist"));
    }
}
