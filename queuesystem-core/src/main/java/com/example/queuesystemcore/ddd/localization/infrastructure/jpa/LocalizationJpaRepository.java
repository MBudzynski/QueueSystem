package com.example.queuesystemcore.ddd.localization.infrastructure.jpa;

import com.example.queuesystemcore.database.entities.Localization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface LocalizationJpaRepository extends JpaRepository<Localization, Long> {

    Localization findByLocationUUID(UUID lcoationUUID);
}
