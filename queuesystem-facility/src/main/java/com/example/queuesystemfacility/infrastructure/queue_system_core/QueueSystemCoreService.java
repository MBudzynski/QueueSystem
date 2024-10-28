package com.example.queuesystemfacility.infrastructure.queue_system_core;

import com.example.queuesystemfacility.common.domain.QueueConfigurationDto;
import com.example.queuesystemfacility.infrastructure.config.httpclient.BaseHttpClient;
import com.example.queuesystemfacility.infrastructure.queue_system_core.response.QueueConfigurationsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class QueueSystemCoreService implements QueueSystemCoreClient {

    private final BaseHttpClient httpQueueSystemCoreClient;

    private static final String FETCH_QUEUE_CONFIGURATION_ENDPOINT = "/queue-configuration";

    @Override
    public List<QueueConfigurationDto> fetchQueueConfigurations(UUID facilityUUID) {
        return httpQueueSystemCoreClient
                .get(FETCH_QUEUE_CONFIGURATION_ENDPOINT,
                        QueueConfigurationsResponse.class,
                        Map.of("facilityUUID", facilityUUID))
                .getQueueConfigurations();
    }
}
