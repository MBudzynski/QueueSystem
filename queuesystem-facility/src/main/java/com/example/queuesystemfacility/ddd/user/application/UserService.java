package com.example.queuesystemfacility.ddd.user.application;

import com.example.queuesystemfacility.ddd.user.domain.User;
import com.example.queuesystemfacility.ddd.user.domain.UserRepository;
import com.example.queuesystemfacility.ddd.user.exception.UserDataNotFoundException;
import com.example.queuesystemfacility.ddd.user.exception.UserException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User findUserByUUID(UUID userUUID) throws UserDataNotFoundException {
        return userRepository.findUserByUUID(userUUID);
    }

    public User findUserDataByLogin(String userLogin, String password) throws UserException {
        User userData = null;
        try {
            userData = userRepository.findUserByUserName(userLogin);
        } catch (UserDataNotFoundException e) {
            throw new UserException();
        }

        //todo add security... and login process
        return userData;
    }

    public User updateUserConfiguration(UUID userUUID, String displayServiceDeskName, String pronouncedServiceDeskName, String pronouncedNumberPrefix) {
        User user = userRepository.findUserByUUID(userUUID);
        return userRepository.saveUser(user.updateConfiguration(
                displayServiceDeskName,
                pronouncedServiceDeskName,
                pronouncedNumberPrefix));
    }
}
