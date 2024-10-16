package com.example.queuesystemcore.ddd.facility.infrastructure.jpa;

import com.example.queuesystemcore.ddd.facility.domain.Facility;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@NoArgsConstructor
@Table(name = "facility")
public class FacilityEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long facilityId;
    private UUID facilityUUID;
    private String institutionName;
    private String city;
    private String street;
    private String houseNumber;
    private String postCode;
    private String postOffice;
    private String pathToLogoFile;
    private String queueName;

    public Facility translate() {
        return new Facility(
                facilityId,
                facilityUUID,
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
