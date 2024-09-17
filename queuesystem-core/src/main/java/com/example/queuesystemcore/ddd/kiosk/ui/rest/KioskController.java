package com.example.queuesystemcore.ddd.kiosk.ui.rest;

import com.example.queuesystemcore.ddd.kiosk.application.KioskService;
import com.example.queuesystemcore.ddd.kiosk.ui.rest.request.KioskRequest;
import com.example.queuesystemcore.ddd.kiosk.ui.rest.response.KioskConfiguration;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/kiosk")
@RequiredArgsConstructor
public class KioskController {

    private final KioskService kioskService;

    @PostMapping( "/find-configuration")
    ResponseEntity<KioskConfiguration> findEkioskConfiguration(@RequestBody @Valid KioskRequest request) {

        KioskConfiguration kioskConfiguration = kioskService.findKioskButtons(
                UUID.fromString(request.getKioskUUID()),
                UUID.fromString(request.getLocalizationUUID())
        );

        return ResponseEntity.ok(kioskConfiguration);
    }
}
