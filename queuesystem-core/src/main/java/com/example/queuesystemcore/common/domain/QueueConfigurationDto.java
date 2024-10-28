package com.example.queuesystemcore.common.domain;

import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
public class QueueConfigurationDto {

    private UUID queueConfigurationUUID;
    private String configurationDescription;
    private String numberRange;
    private String sign;
}
