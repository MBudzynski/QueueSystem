package com.example.queuesystemcore.ddd.ekiosk.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class KioskDto {

    private Long kioskId;
    private String kioskUUID;
    private Long localizationId;
    private String kioskName;
    private String kioskDescription;
    private String backgroundColor;
    private List<KioskButtonDto> kioskButtons;
}
