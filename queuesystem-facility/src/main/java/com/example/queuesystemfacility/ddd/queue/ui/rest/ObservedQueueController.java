package com.example.queuesystemfacility.ddd.queue.ui.rest;

import com.example.queuesystemfacility.common.application.ObservedQueueFacade;
import com.example.queuesystemfacility.ddd.queue.ui.rest.request.SaveObservedQueueRequest;
import com.example.queuesystemfacility.ddd.queue.ui.rest.response.ObservedUserQueueResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/observed-queue")
@RequiredArgsConstructor
public class ObservedQueueController {

    private final ObservedQueueFacade observedQueueFacade;

    @GetMapping
    public ObservedUserQueueResponse getObservedUserQueue(@PathVariable UUID userUUID) {
        return ObservedUserQueueResponse.valueOf(observedQueueFacade.getObservedUserQueue(userUUID));
    }

    @PostMapping
    public void saveObservedUserQueue(@RequestBody @Valid SaveObservedQueueRequest request) {
        observedQueueFacade.saveUserObservedQueue(request.getUserUUID(), request.getQueueConfigurationUUIDs());
    }
}
