package com.example.queuesystemcore.ddd.kiosk.infrastructure.jpa.kioskbutton;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KioskButtonJpaRepository extends JpaRepository<KioskButtonEntity, Long> {
}
