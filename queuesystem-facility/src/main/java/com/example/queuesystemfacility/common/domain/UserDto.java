package com.example.queuesystemfacility.common.domain;

import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
public class UserDto {

    private Long userId;
    private UUID userUUID;
    private String userLogin;
    private String userPassword;
    private String displayServiceDeskName;
    private String pronouncedNumberPrefix;
    private String pronouncedServiceDeskName;
    private String displayDeviceIp;

}
