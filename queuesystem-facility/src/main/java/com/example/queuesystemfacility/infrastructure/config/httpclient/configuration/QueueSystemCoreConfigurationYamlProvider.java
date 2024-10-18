package com.example.queuesystemfacility.infrastructure.config.httpclient.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
class QueueSystemCoreConfigurationYamlProvider implements QueueSystemCoreConfigurationProvider {

    @Value("${http.client.queue-system-core.base-url}")
    private String baseHttpUrl;

    @Override
    public String getBaseHttpUrl() {
        return baseHttpUrl;
    }
}
