package com.example.queuesystemfacility.ddd.queue.ui.rest.response;

import com.example.queuesystemfacility.common.domain.QueueNumberDto;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class QueueNumberResponse {

    private QueueNumberDto queueNumber;

    public static QueueNumberResponse valueOf(QueueNumberDto queueNumber) {
        return new QueueNumberResponse(queueNumber);
    }
}
