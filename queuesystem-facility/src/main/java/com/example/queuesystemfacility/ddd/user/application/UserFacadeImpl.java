package com.example.queuesystemfacility.ddd.user.application;

import com.example.queuesystemfacility.common.application.UserFacade;
import com.example.queuesystemfacility.common.domain.UserDto;
import com.example.queuesystemfacility.ddd.user.exception.UserDataNotFoundException;
import com.example.queuesystemfacility.ddd.user.exception.UserException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@AllArgsConstructor
public class UserFacadeImpl implements UserFacade {

    private final UserService userService;

    public UserDto findUserByUUID(UUID userUUID) throws UserDataNotFoundException {
        return userService
                .findUserByUUID(userUUID)
                .translate();
    }

    @Override
    public UserDto getUserConfiguration(String userLogin) throws UserException {
        return userService.findUserDataByLogin(userLogin)
                .translate();
    }

    @Override
    public UserDto updateUserConfiguration(UUID userUUID, String displayServiceDeskName, String pronouncedServiceDeskName, String pronouncedNumberPrefix) {
        return userService.updateUserConfiguration(userUUID, displayServiceDeskName, pronouncedServiceDeskName, pronouncedNumberPrefix).translate();
    }
}
