package com.example.queuesystemcore.ddd.queue.ui.rest;

import com.example.queuesystemcore.common.application.QueueFacade;
import com.example.queuesystemcore.common.domain.QueueNumberDto;
import com.example.queuesystemcore.ddd.queue.ui.rest.request.QueueRequest;
import com.example.queuesystemcore.ddd.queue.ui.rest.response.QueueNumberResponse;
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
@RequestMapping("/queue")
@RequiredArgsConstructor
public class QueueController {

    private final QueueFacade queueFacade;

    @PostMapping(path = "/add")
    ResponseEntity<QueueNumberResponse> queuePetitioner(@RequestBody @Valid QueueRequest request) {

        QueueNumberDto number = queueFacade.queuePetitioner(
                UUID.fromString(request.getQueueConfigurationUUID()),
                UUID.fromString(request.getLocalizationUUID()));

        return ResponseEntity.ok(QueueNumberResponse.valueOf(number.getQueueNumber(), number.getQueueNumberPdf()));
    }
}
