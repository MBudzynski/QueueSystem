package com.example.queuesystemcore.ddd.localization.infrastructure.jpa;

import com.example.queuesystemcore.ddd.localization.domain.Localization;
import com.example.queuesystemcore.ddd.localization.domain.LocalizationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class LocalizationAdapter implements LocalizationRepository {

    private final LocalizationJpaRepository repository;

    @Override
    public Optional<Localization> findLocalizationByUUID(UUID localizationUUID) {
        return Optional
                .ofNullable(repository.findByLocationUUID(localizationUUID))
                .map(LocalizationEntity::translate);
    }

    @Override
    public List<String> getAllQueueNames() {
        return repository.getAllQueueNames()
                .stream()
                .filter(queueName -> queueName != null && !queueName.isBlank())
                .toList();
    }
}
