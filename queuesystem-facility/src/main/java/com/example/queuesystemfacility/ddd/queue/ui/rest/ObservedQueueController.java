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
    public ObservedUserQueueResponse getUserQueueObserved(@RequestParam UUID userUUID) {
        return ObservedUserQueueResponse.valueOf(observedQueueFacade.getUserQueueObserved(userUUID));
    }

    @PostMapping
    public void saveUserQueueObserved(@RequestBody @Valid SaveObservedQueueRequest request) {
        observedQueueFacade.saveUserQueueObserved(request.getUserUUID(), request.getQueueConfigurationUUIDs());
    }
}
