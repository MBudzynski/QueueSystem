package com.example.queuesystemcore.database.entities;

import com.example.queuesystemcore.ddd.ekiosk.domain.KioskDto;
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

    public KioskDto translate() {
        return new KioskDto (
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
