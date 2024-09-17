package com.example.queuesystemcore.ddd.ekiosk.infrastructure.jpa.kiosk;

import com.example.queuesystemcore.database.entities.Kiosk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface KioskJpaRepository extends JpaRepository<Kiosk, Long> {

    @Query(value = "SELECT * FROM kiosk WHERE kioskuuid like :uuid AND localization_id = :localizationId",nativeQuery = true)
    Optional<Kiosk> findKioskByUuidAndLocalization(String uuid, Long localizationId);
}
