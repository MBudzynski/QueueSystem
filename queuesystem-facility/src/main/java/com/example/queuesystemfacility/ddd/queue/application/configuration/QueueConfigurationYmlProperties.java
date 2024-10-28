package com.example.queuesystemfacility.ddd.queue.application.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class QueueConfigurationYmlProperties implements QueueConfigurationProvider {

    @Value("${custom.queue.delay-time-minutes}")
    private Long delayTimeMinutes;

    @Override
    public Long delayTimeMinutes() {
        return delayTimeMinutes;
    }
}
