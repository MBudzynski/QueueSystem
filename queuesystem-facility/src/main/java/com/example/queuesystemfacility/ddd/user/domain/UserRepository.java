package com.example.queuesystemfacility.ddd.user.domain;

import com.example.queuesystemfacility.ddd.user.exception.UserDataNotFoundException;

import java.util.UUID;

public interface UserRepository {

    User findUserByUUID(UUID userUUID) throws UserDataNotFoundException;

    User findUserByUserName(String userLogin) throws UserDataNotFoundException;

    User saveUser(User user);
}
