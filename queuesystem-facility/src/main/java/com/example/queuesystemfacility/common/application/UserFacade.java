package com.example.queuesystemfacility.common.application;

import com.example.queuesystemfacility.common.domain.UserDto;
import com.example.queuesystemfacility.ddd.user.exception.UserDataNotFoundException;
import com.example.queuesystemfacility.ddd.user.exception.UserException;

import java.util.UUID;

public interface UserFacade {

    UserDto findUserByUUID(UUID userUUID) throws UserDataNotFoundException;

    UserDto getUserConfiguration(String userLogin) throws UserException ;

    UserDto updateUserConfiguration(UUID userUUID, String displayServiceDeskName, String pronouncedServiceDeskName, String pronouncedNumberPrefix);
}
