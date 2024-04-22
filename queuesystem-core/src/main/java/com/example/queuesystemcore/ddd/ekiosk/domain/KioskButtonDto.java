package com.example.queuesystemcore.ddd.ekiosk.domain;

import lombok.Data;

import java.util.List;

@Data
public class KioskButtonDto {

    private Long kioskButtonId;
    private Long kioskSubButtonId;
    private Long buttonText;
    private String queueConfigurationId;
    private String kioskDescription;
    private List<KioskButtonDto> kioskButtonDto;

    public KioskButtonDto(Long kioskButtonId,
                          Long kioskSubButtonId,
                          Long buttonText,
                          String queueConfigurationId,
                          String kioskDescription) {
        this.kioskButtonId = kioskButtonId;
        this.kioskSubButtonId = kioskSubButtonId;
        this.buttonText = buttonText;
        this.queueConfigurationId = queueConfigurationId;
        this.kioskDescription = kioskDescription;
    }
}
