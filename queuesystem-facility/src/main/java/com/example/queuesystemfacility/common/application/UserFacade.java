package com.example.queuesystemfacility.common.application;

import com.example.queuesystemfacility.ddd.user.exception.UserException;

import java.util.UUID;

public interface UserFacade {

    Long findUserIdByUUID(UUID userUUID) throws UserException;
}
