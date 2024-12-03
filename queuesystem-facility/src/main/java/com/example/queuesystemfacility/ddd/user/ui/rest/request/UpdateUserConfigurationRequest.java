package com.example.queuesystemfacility.ddd.user.ui.rest.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserConfigurationRequest {

    @NotNull
    private UUID userUUID;
    private String displayServiceDeskName;
    private String pronouncedNumberPrefix;
    private String pronouncedServiceDeskName;
}
