package com.example.queuesystemcore.ddd.ekiosk.ui.rest;

import com.example.queuesystemcore.ddd.ekiosk.application.KioskService;
import com.example.queuesystemcore.ddd.ekiosk.ui.rest.request.EKioskRequest;
import com.example.queuesystemcore.ddd.ekiosk.ui.rest.response.EKioskConfiguration;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Slf4j
@RestController(value = "/kiosk")
@RequiredArgsConstructor
public class KioskController {

    private final KioskService kioskService;

    @PostMapping( "/find-configuration")
    ResponseEntity<EKioskConfiguration> findEkioskConfiguration(@RequestBody @Valid EKioskRequest request) {

        EKioskConfiguration eKioskConfiguration = kioskService.findKioskButtons(
                UUID.fromString(request.getKioskUUID()),
                UUID.fromString(request.getLocalizationUUID())
        );

        return ResponseEntity.ok(eKioskConfiguration);
    }
}
