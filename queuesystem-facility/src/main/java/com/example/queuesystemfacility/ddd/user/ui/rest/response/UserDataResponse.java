package com.example.queuesystemfacility.ddd.user.ui.rest.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
public class UserDataResponse {

    private UUID userUUID;
    private String userLogin;
    private String displayServiceDeskName;
    private String pronouncedNumberPrefix;
    private String pronouncedServiceDeskName;
}
