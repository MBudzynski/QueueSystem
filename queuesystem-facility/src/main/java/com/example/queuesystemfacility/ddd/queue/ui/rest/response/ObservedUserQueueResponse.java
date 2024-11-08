package com.example.queuesystemfacility.ddd.queue.ui.rest.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class ObservedUserQueueResponse {

    private List<UUID> observedQueues;

    public static ObservedUserQueueResponse valueOf(List<UUID> observedQueues) {
        return new ObservedUserQueueResponse(observedQueues);
    }
}
