package com.example.queuesystemfacility.ddd.queue.ui.rest;

import com.example.queuesystemfacility.common.application.QueueFacade;
import com.example.queuesystemfacility.ddd.queue.ui.rest.request.DelayNumberRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/queue")
@RequiredArgsConstructor
public class QueueController {

    private final QueueFacade queueFacade;

    @PostMapping("/bring")
    public void bringNextNumber() {
        //todo implement logic
    }

    @PatchMapping("/delay")
    public void delayNumber(@RequestBody @Valid DelayNumberRequest request) {
        queueFacade.delayQueueNumber(request.getQueueUUID());
    }

    @DeleteMapping
    public void deleteNumber(@RequestParam UUID queueUUID) {
        queueFacade.deleteQueueNumber(queueUUID);
    }

    @GetMapping("/show")
    public void showQueue() {
        //todo implement logic
    }

}
