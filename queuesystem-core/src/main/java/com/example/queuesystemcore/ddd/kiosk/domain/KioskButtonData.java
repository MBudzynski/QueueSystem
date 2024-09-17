package com.example.queuesystemcore.ddd.kiosk.domain;

import lombok.Data;

@Data
public class KioskButtonData {

    private Long kioskButtonId;
    private Long kioskSubButtonId;
    private String buttonText;
    private String queueConfigurationUUID;

    public KioskButtonData(Long kioskButtonId,
                           Long kioskSubButtonId,
                           String buttonText,
                           String queueConfigurationUUID) {
        this.kioskButtonId = kioskButtonId;
        this.kioskSubButtonId = kioskSubButtonId;
        this.buttonText = buttonText;
        this.queueConfigurationUUID = queueConfigurationUUID;
    }
}
