package com.example.queuesystemfacility.infrastructure.config.httpclient;

import com.example.queuesystemfacility.infrastructure.config.httpclient.configuration.QueueSystemCoreConfigurationProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@RequiredArgsConstructor
public class WebClientConfiguration {

    private final QueueSystemCoreConfigurationProvider queueSystemCoreConfigurationProvider;

    @Bean
    public WebClient queueSystemCoreWebClient(WebClient.Builder builder) {
        return builder
                .baseUrl(queueSystemCoreConfigurationProvider.getBaseHttpUrl())
                .build();
    }

    @Bean
    public BaseHttpClient httpQueueSystemCoreClient(@Qualifier("queueSystemCoreWebClient") WebClient webClient) {
        return new BaseHttpClient(webClient);
    }

}
