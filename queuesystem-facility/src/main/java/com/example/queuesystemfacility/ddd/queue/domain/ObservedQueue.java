package com.example.queuesystemfacility.ddd.queue.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
public class ObservedQueue {
    private Long id;
    private Long userId;
    private Long queueConfigurationId;
    private UUID queueConfigurationUUID;
}
