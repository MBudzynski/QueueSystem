package com.example.queuesystemcore.ddd.kiosk.infrastructure.jpa.kioskbutton;

import com.example.queuesystemcore.ddd.kiosk.domain.KioskButton;
import com.example.queuesystemcore.ddd.kiosk.infrastructure.jpa.kiosk.KioskEntity;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "kiosk_button")
@NoArgsConstructor
public class KioskButtonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long kioskButtonId;
    private Long kioskSubButtonId;
    private String buttonText;
    private String queueConfigurationUUID;

    @ManyToOne
    @JoinColumn(name="kiosk_id", nullable=false)
    private KioskEntity kiosk;

    public KioskButton translate() {
        return new KioskButton(
                kioskButtonId,
                kioskSubButtonId,
                buttonText,
                queueConfigurationUUID
        );
    }
}
