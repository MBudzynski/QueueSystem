package com.example.queuesystemfacility.ddd.user.ui.rest.response;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginRequest {

    @NotBlank
    private String userLogin;
    @NotBlank
    private String password;
}
