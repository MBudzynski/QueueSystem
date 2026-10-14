package com.example.queuesystemfacility.ddd.user.domain;

import com.example.queuesystemfacility.common.domain.UserDto;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
public class User {

    private Long userId;
    private UUID userUUID;
    private String userLogin;
    private String userPassword;
    private String displayServiceDeskName;
    private String pronouncedNumberPrefix;
    private String pronouncedServiceDeskName;
    private String displayDeviceIp;

    public UserDto translate() {
        return UserDto
                .builder()
                .userId(userId)
                .userUUID(userUUID)
                .userLogin(userLogin)
                .userPassword(userPassword)
                .displayServiceDeskName(displayServiceDeskName)
                .pronouncedNumberPrefix(pronouncedNumberPrefix)
                .pronouncedServiceDeskName(pronouncedServiceDeskName)
                .displayDeviceIp(displayDeviceIp)
                .build();
    }

    public User updateConfiguration(String displayServiceDeskName, String pronouncedServiceDeskName, String pronouncedNumberPrefix) {
        this.displayServiceDeskName = displayServiceDeskName;
        this.pronouncedNumberPrefix = pronouncedNumberPrefix;
        this.pronouncedServiceDeskName = pronouncedServiceDeskName;
        return this;
    }
}
