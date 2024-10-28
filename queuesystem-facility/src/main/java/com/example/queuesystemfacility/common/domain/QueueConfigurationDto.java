package com.example.queuesystemfacility.common.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Builder
public class QueueConfigurationDto {

    private UUID queueConfigurationUUID;
    private String configurationDescription;
    private String numberRange;
    private String sign;
}
