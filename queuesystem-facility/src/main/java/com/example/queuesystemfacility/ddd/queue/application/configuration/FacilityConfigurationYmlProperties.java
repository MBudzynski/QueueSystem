package com.example.queuesystemfacility.ddd.queue.application.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
class FacilityConfigurationYmlProperties implements FacilityConfigurationProvider {

    @Value("${custom.configuration.queue-name}")
    private String queueName;

    @Value("${custom.configuration.facilityId}")
    private Long facilityId;

    @Override
    public String getQueueName() {
        return queueName;
    }

    @Override
    public Long facilityId() {
        return facilityId;
    }
}
