package com.example.queuesystemcore.ddd.queue.ui.rest.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QueueNumberResponse {
    private String number;
    private String numberPdf;

    public static QueueNumberResponse valueOf(String queueNumber, String queueNumberPdf){
        return new QueueNumberResponse(queueNumber, queueNumberPdf);
    }
}
