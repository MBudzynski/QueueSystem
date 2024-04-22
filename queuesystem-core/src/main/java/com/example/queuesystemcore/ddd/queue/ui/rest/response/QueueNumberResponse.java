package com.example.queuesystemcore.ddd.queue.ui.rest.response;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class QueueNumberResponse {
    private String queueNumber;

    public static QueueNumberResponse valueOf(String queueNumber){
        return new QueueNumberResponse(queueNumber);
    }
}
