package com.example.queuesystemcore.ddd.kiosk.application;

import com.example.queuesystemcore.ddd.kiosk.domain.KioskButtonRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class KioskButtonProvider {

    private final KioskButtonRepository kioskButtonRepository;
}
