package com.example.queuesystemfacility.ddd.queue.application.configuration;

import java.util.UUID;

public interface FacilityConfigurationProvider {

    String getQueueName();

    Long facilityId();

    UUID facilityUUID();
}
