package com.example.queuesystemcore.ddd.kiosk.infrastructure.jpa.kioskbutton;

import com.example.queuesystemcore.ddd.kiosk.domain.KioskButtonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KioskButtonAdapter implements KioskButtonRepository {

    private final KioskButtonJpaRepository repository;
}
