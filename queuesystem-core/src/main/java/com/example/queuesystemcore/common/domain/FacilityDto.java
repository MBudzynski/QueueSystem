package com.example.queuesystemcore.common.domain;

import lombok.Builder;
import lombok.Getter;

import java.util.UUID;
@Getter
@Builder
public class FacilityDto {
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
