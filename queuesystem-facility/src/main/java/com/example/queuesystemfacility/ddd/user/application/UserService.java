package com.example.queuesystemfacility.ddd.user.application;

import com.example.queuesystemfacility.ddd.user.domain.User;
import com.example.queuesystemfacility.ddd.user.domain.UserRepository;
import com.example.queuesystemfacility.ddd.user.exception.UserException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User findUserByUUID(UUID userUUID) throws UserException {
        return userRepository.findUserByUUID(userUUID);
    }
}
