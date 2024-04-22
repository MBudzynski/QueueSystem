package com.example.queuesystemcore.ddd.localization.infrastructure.jpa;

import com.example.queuesystemcore.database.entities.Localization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocalizationJpaRepository extends JpaRepository<Localization, Long> {

    Localization findByUUID(String uuid);
}
