package com.example.queuesystemfacility.ddd.queue.application;

import com.example.queuesystemfacility.common.application.QueueFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
class QueueFacadeImpl implements QueueFacade {

    private final QueueService queueService;

    public void deleteQueueNumber(UUID queueUUID) {
        queueService.deleteQueueNumber(queueUUID);
    }

    @Override
    public void delayQueueNumber(UUID queueUUID) {
        queueService.delayQueueNumber(queueUUID);
    }
}
