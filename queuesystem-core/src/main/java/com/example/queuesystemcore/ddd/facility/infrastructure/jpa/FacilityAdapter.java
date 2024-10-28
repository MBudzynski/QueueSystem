package com.example.queuesystemcore.ddd.facility.infrastructure.jpa;

import com.example.queuesystemcore.ddd.facility.domain.Facility;
import com.example.queuesystemcore.ddd.facility.domain.FacilityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class FacilityAdapter implements FacilityRepository {

    private final FacilityJpaRepository repository;

    @Override
    public Optional<Facility> findFacilityByUUID(UUID facilityUUID) {
        return Optional
                .ofNullable(repository.findByFacilityUUID(facilityUUID))
                .map(FacilityEntity::translate);
    }

    @Override
    public List<String> getAllQueueNames() {
        return repository.getAllQueueNames()
                .stream()
                .filter(queueName -> queueName != null && !queueName.isBlank())
                .toList();
    }
}
