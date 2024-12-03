package com.example.queuesystemfacility.ddd.user.ui.rest;

import com.example.queuesystemfacility.ddd.user.ui.rest.request.UserLoginRequest;
import com.example.queuesystemfacility.infrastructure.config.security.JwtTokenUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user/login")
@RequiredArgsConstructor
public class UserAuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;

    @PostMapping
    public String login(@RequestBody @Valid UserLoginRequest request) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        encoder.encode(request.getPassword());
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getUserLogin(),
                request.getPassword()));
        return jwtTokenUtil.generateToken(request.getUserLogin());
    }

}
