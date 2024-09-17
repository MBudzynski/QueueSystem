package com.example.queuesystemcore.ddd.kiosk.ui.rest.response;

import com.example.queuesystemcore.ddd.kiosk.domain.KioskButtonData;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class KioskConfiguration {

    private String institutionName;
    private String city;
    private String street;
    private String houseNumber;
    private String postCode;
    private String postOffice;
    private String institutionLogoFile;
    private String backgroundColor;
    private List<KioskButtonData> kioskButtons;
}
