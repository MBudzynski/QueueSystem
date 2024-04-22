package com.example.queuesystemcore.ddd.ekiosk.ui.rest.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class KioskRequest {

    private String localizationUUID;
    private String kioskUUID;
}
