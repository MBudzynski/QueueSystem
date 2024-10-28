package com.example.queuesystemfacility.ddd.user.infrastructure.jpa.user;

import com.example.queuesystemfacility.ddd.user.domain.User;
import com.example.queuesystemfacility.ddd.user.domain.UserRepository;
import com.example.queuesystemfacility.ddd.user.exception.UserException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class UserAdapter implements UserRepository {

    private final UserJpaRepository userJpaRepository;

    public User findUserByUUID(UUID userUUID) throws UserException {
        return userJpaRepository
                .findUserEntitiesByUserUUID(userUUID)
                .map(UserEntity::translateTo)
                .orElseThrow(()-> new UserException("User not found"));
    }
}
