package com.example.queuesystemfacility.infrastructure.config.security;

import com.example.queuesystemfacility.common.domain.CustomUserDetails;
import com.example.queuesystemfacility.ddd.user.application.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;

    public CustomUserDetails authenticate(String userLogin, String password) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userLogin,
                        password
                )
        );

        return Optional.of(new CustomUserDetails(userService.findUserDataByLogin(userLogin))
        ).orElseThrow();
    }
}
