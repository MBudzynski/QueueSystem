package com.example.queuesystemcore.ddd.localization.infrastructure.jpa;

import com.example.queuesystemcore.ddd.localization.domain.Localization;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@NoArgsConstructor
@Table(name = "localization")
public class LocalizationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long locationId;
    private UUID locationUUID;
    private String institutionName;
    private String city;
    private String street;
    private String houseNumber;
    private String postCode;
    private String postOffice;
    private String pathToLogoFile;
    private String queueName;

    public Localization translate() {
        return new Localization(
                locationId,
                locationUUID,
                institutionName,
                city,
                street,
                houseNumber,
                postCode,
                postOffice,
                pathToLogoFile,
                queueName);
    }
}
