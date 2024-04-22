package com.example.queuesystemcore.ddd.ekiosk.infrastructure.jpa.kioskbutton;

import com.example.queuesystemcore.database.entities.KioskButton;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KioskButtonJpaRepository extends JpaRepository<KioskButton, Long> {
}
