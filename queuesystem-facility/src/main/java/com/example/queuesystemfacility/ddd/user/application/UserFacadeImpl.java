package com.example.queuesystemfacility.ddd.user.application;

import com.example.queuesystemfacility.common.application.UserFacade;
import com.example.queuesystemfacility.ddd.user.exception.UserException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@AllArgsConstructor
public class UserFacadeImpl implements UserFacade {

    private final UserService userService;

    public Long findUserIdByUUID(UUID userUUID) throws UserException {
        return userService
                .findUserByUUID(userUUID)
                .getUserId();
    }
}
