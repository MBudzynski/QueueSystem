package com.example.queuesystemcore.ddd.kiosk.infrastructure.jpa.kiosk;

import com.example.queuesystemcore.ddd.kiosk.infrastructure.jpa.kioskbutton.KioskButtonEntity;
import com.example.queuesystemcore.ddd.kiosk.domain.Kiosk;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@NoArgsConstructor
@Table(name = "kiosk")
public class KioskEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long kioskId;
    private String kioskUUID;
    private Long facilityId;
    private String kioskName;
    private String kioskDescription;
    private String backgroundColor;
    @OneToMany(mappedBy="kiosk")
    private List<KioskButtonEntity> kioskButtons;

    public Kiosk translate() {
        return new Kiosk(
                kioskId,
                kioskUUID,
                facilityId,
                kioskName,
                kioskDescription,
                backgroundColor,
                kioskButtons
                        .stream()
                        .map(KioskButtonEntity::translate)
                        .toList());
    }
}
