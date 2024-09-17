package com.example.queuesystemcore.database.entities;

import com.example.queuesystemcore.ddd.ekiosk.domain.KioskButtonDto;
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

    public KioskButtonDto translate() {
        return new KioskButtonDto (
                kioskButtonId,
                kioskSubButtonId,
                buttonText,
                queueConfigurationUUID
        );
    }
}
