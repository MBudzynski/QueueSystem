package com.example.queuesystemcore.database.entities;

import com.example.queuesystemcore.ddd.localization.domain.LocalizationDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@NoArgsConstructor
public class Localization {

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

    public LocalizationDto translate() {
        return new LocalizationDto(
                locationId,
                locationUUID,
                institutionName,
                city,
                street,
                houseNumber,
                postCode,
                postOffice,
                pathToLogoFile);
    }
}
