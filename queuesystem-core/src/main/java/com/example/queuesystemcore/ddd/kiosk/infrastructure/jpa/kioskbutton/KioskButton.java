package com.example.queuesystemcore.ddd.kiosk.infrastructure.jpa.kioskbutton;

import com.example.queuesystemcore.ddd.kiosk.domain.KioskButtonData;
import com.example.queuesystemcore.ddd.kiosk.infrastructure.jpa.kiosk.Kiosk;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public class KioskButton {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long kioskButtonId;
    private Long kioskSubButtonId;
    private String buttonText;
    private String queueConfigurationUUID;

    @ManyToOne
    @JoinColumn(name="kiosk_id", nullable=false)
    private Kiosk kiosk;

    public KioskButtonData translate() {
        return new KioskButtonData(
                kioskButtonId,
                kioskSubButtonId,
                buttonText,
                queueConfigurationUUID
        );
    }
}
