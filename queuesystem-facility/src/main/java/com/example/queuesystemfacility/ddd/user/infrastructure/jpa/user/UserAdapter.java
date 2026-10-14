package com.example.queuesystemfacility.ddd.user.infrastructure.jpa.user;

import com.example.queuesystemfacility.ddd.user.domain.User;
import com.example.queuesystemfacility.ddd.user.domain.UserRepository;
import com.example.queuesystemfacility.ddd.user.exception.UserDataNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class UserAdapter implements UserRepository {

    private final UserJpaRepository userJpaRepository;

    public User findUserByUUID(UUID userUUID) {
        return userJpaRepository
                .findUserEntitiesByUserUUID(userUUID)
                .map(UserEntity::translateTo)
                .orElseThrow(()-> new UserDataNotFoundException("User not found"));
    }

    @Override
    public User findUserByUserName(String userLogin) throws UserDataNotFoundException {
        return userJpaRepository
                .findUserEntitiesByUserLogin(userLogin)
                .map(UserEntity::translateTo)
                .orElseThrow(()-> new UserDataNotFoundException("User data not found"));
    }

    @Override
    public User saveUser(User user) {
        UserEntity entity = new UserEntity();
        entity.mutateTo(user);
        return userJpaRepository.save(entity).translateTo();
    }
}
