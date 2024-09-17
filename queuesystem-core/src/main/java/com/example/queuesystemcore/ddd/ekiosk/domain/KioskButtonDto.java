package com.example.queuesystemcore.ddd.ekiosk.domain;

import lombok.Data;

import java.util.List;

@Data
public class KioskButtonDto {

    private Long kioskButtonId;
    private Long kioskSubButtonId;
    private String buttonText;
    private String queueConfigurationUUID;

    public KioskButtonDto(Long kioskButtonId,
                          Long kioskSubButtonId,
                          String buttonText,
                          String queueConfigurationUUID) {
        this.kioskButtonId = kioskButtonId;
        this.kioskSubButtonId = kioskSubButtonId;
        this.buttonText = buttonText;
        this.queueConfigurationUUID = queueConfigurationUUID;
    }
}
