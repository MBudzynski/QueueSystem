package com.example.queuesystemcore.ddd.kiosk.infrastructure.jpa.kiosk;

import com.example.queuesystemcore.ddd.kiosk.infrastructure.jpa.kioskbutton.KioskButton;
import com.example.queuesystemcore.ddd.kiosk.domain.KioskData;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@NoArgsConstructor
public class Kiosk {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long kioskId;
    private String kioskUUID;
    private Long localizationId;
    private String kioskName;
    private String kioskDescription;
    private String backgroundColor;
    @OneToMany(mappedBy="kiosk")
    private List<KioskButton> kioskButtons;

    public KioskData translate() {
        return new KioskData(
                kioskId,
                kioskUUID,
                localizationId,
                kioskName,
                kioskDescription,
                backgroundColor,
                kioskButtons
                        .stream()
                        .map(KioskButton::translate)
                        .toList());
    }
}
