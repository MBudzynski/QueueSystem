package com.example.queuesystemcore.ddd.ekiosk.ui.rest;

import com.example.queuesystemcore.ddd.ekiosk.application.KioskService;
import com.example.queuesystemcore.ddd.ekiosk.domain.KioskDto;
import com.example.queuesystemcore.ddd.ekiosk.ui.rest.request.KioskRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Slf4j
@Service
@RestController("/kiosk")
@RequiredArgsConstructor
public class KioskController {

    private final KioskService kioskService;

    @PostMapping(path = "/find")
    ResponseEntity<KioskDto> queuePetitioner(@RequestBody KioskRequest request) {

        KioskDto kioskDto = kioskService.findKioskButtons(
                UUID.fromString(request.getKioskUUID()),
                UUID.fromString(request.getLocalizationUUID())
        );

        return ResponseEntity.ok(kioskDto);
    }
}
