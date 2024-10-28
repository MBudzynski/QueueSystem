package com.example.queuesystemcore.ddd.queue.ui.rest.response;

import com.example.queuesystemcore.common.domain.QueueConfigurationDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class QueueConfigurationsResponse {

    List<QueueConfigurationDto> queueConfigurations;

    public static QueueConfigurationsResponse valueOf(List<QueueConfigurationDto> queueConfigurations) {
        return new QueueConfigurationsResponse(queueConfigurations);
    }
}
