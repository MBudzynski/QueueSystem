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
    private String UUID;
    private Long localizationId;
    private String kioskName;
    private String kioskDescription;

    @OneToMany(mappedBy="kiosk")
    private List<KioskButton> kioskButtons;

    public KioskDto translate() {
        return new KioskDto (
                kioskId,
                UUID,
                localizationId,
                kioskName,
                kioskDescription,
                kioskButtons
                        .stream()
                        .map(KioskButton::translate)
                        .toList());
    }
}
