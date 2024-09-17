package com.example.queuesystemcore.ddd.localization.infrastructure.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface LocalizationJpaRepository extends JpaRepository<Localization, Long> {

    Localization findByLocationUUID(UUID lcoationUUID);
}
