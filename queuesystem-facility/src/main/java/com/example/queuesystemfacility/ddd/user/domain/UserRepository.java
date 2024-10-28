package com.example.queuesystemfacility.ddd.user.domain;

import com.example.queuesystemfacility.ddd.user.exception.UserException;

import java.util.UUID;

public interface UserRepository {

    User findUserByUUID(UUID userUUID) throws UserException;
}
