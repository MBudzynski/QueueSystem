package com.example.queuesystemcore.ddd.kiosk.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class KioskButtonData {

    private Long kioskButtonId;
    private Long kioskSubButtonId;
    private String buttonText;
    private String queueConfigurationUUID;
}
