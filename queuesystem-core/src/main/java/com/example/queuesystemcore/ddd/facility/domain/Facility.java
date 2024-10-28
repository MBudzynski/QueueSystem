package com.example.queuesystemcore.ddd.facility.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class Facility {

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
}
