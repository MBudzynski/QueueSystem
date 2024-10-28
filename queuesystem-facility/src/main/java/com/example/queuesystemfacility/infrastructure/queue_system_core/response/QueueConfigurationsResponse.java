package com.example.queuesystemfacility.infrastructure.queue_system_core.response;

import com.example.queuesystemfacility.common.domain.QueueConfigurationDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class QueueConfigurationsResponse {

    List<QueueConfigurationDto> queueConfigurations;
}
