package com.example.queuesystemfacility.ddd.queue.domain;

import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

public interface QueueRepository {

    void addToQueue(Queue queue);

    void deleteQueueByUUID(UUID queueUUID);

    void delayQueueNumber(UUID queueUUID, LocalTime now);

    Queue findNextNumber(Long userId);

    List<Queue> showAllNumbers(Long userId);
}
