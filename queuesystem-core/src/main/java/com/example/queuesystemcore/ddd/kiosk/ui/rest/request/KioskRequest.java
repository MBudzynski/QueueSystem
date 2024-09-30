package com.example.queuesystemcore.ddd.kiosk.ui.rest.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class KioskRequest {

    @NotNull
    @NotBlank
    private String localizationUUID;

    @NotNull
    @NotBlank
    private String kioskUUID;
}