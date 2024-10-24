package com.example.queuesystemfacility.ddd.queue.ui.rest;

import com.example.queuesystemfacility.common.application.QueueFacade;
import com.example.queuesystemfacility.common.domain.QueueNumberDto;
import com.example.queuesystemfacility.ddd.queue.ui.rest.request.BringNextNumberRequest;
import com.example.queuesystemfacility.ddd.queue.ui.rest.request.DelayNumberRequest;
import com.example.queuesystemfacility.ddd.queue.ui.rest.response.QueueNumberResponse;
import com.example.queuesystemfacility.ddd.queue.ui.rest.response.QueueNumbersResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/queue")
@RequiredArgsConstructor
public class QueueController {

    private final QueueFacade queueFacade;

    @PostMapping("/bring")
    @ResponseStatus(HttpStatus.OK)
    public QueueNumberResponse bringNextNumber(@RequestBody @Valid BringNextNumberRequest request) {
        QueueNumberDto dto = queueFacade.bringNextNumber(request.getUserUUID());
        return QueueNumberResponse.valueOf(dto);
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
    public QueueNumbersResponse showQueue(@RequestParam UUID userUUID) {
        return QueueNumbersResponse.valueOf(queueFacade.showAllNumbers(userUUID));
    }

}
