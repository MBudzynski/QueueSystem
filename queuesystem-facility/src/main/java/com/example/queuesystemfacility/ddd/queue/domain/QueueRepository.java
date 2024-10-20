package com.example.queuesystemfacility.ddd.queue.domain;

import java.time.LocalDateTime;
import java.util.UUID;

public interface QueueRepository {

    void addToQueue(Queue queue);

    void deleteQueueByUUID(UUID queueUUID);

    void delayQueueNumber(UUID queueUUID, LocalDateTime now);
}
