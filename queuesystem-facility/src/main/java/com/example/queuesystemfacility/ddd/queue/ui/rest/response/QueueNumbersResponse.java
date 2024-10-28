package com.example.queuesystemfacility.ddd.queue.ui.rest.response;

import com.example.queuesystemfacility.common.domain.QueueNumberDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class QueueNumbersResponse {

    private List<QueueNumberDto> queueNumbers;

    public static QueueNumbersResponse valueOf(List<QueueNumberDto> queueNumbers) {
        return new QueueNumbersResponse(queueNumbers);
    }
}
