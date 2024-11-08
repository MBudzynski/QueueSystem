package com.example.queuesystemfacility.ddd.queue.ui.rest.response;

import com.example.queuesystemfacility.common.domain.QueueConfigurationDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class QueueConfigurationResponse {

    private List<QueueConfigurationDto> configuredQueues;

    public static QueueConfigurationResponse valueOf(List<QueueConfigurationDto> dtos) {
        return new QueueConfigurationResponse(dtos);
    }
}
