package com.example.queuesystemfacility.infrastructure.config.httpclient;

import lombok.RequiredArgsConstructor;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriBuilder;

import java.util.Map;

@RequiredArgsConstructor
public class BaseHttpClient {

    private final WebClient webClient;

    public <T> T get(String uri, Class<T> responseType, Map<String, Object> queryParams) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder(uriBuilder, uri, queryParams).build())
                .header("Accept", "application/json")
                .retrieve()
                .bodyToMono(responseType)
                .block();
    }

    private UriBuilder uriBuilder(UriBuilder uriBuilder, String uri, Map<String, Object> queryParams) {
        UriBuilder builder = uriBuilder.path(uri);
        for (Map.Entry<String, Object> entry : queryParams.entrySet()) {
            builder = builder.queryParam(entry.getKey(), entry.getValue().toString());
        }
        return uriBuilder;
    }
}
