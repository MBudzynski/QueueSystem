package com.example.queuesystemfacility.infrastructure.config.security.properties;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
@RequiredArgsConstructor
public class JwtSecretProviderImpl implements JwtSecretProvider {

    @Value("${security.jwt.secret-base64}")
    private String secret;
}
