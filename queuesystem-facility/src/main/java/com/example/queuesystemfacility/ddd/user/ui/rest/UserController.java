package com.example.queuesystemfacility.ddd.user.ui.rest;

import com.example.queuesystemfacility.common.application.UserFacade;
import com.example.queuesystemfacility.common.domain.UserDto;
import com.example.queuesystemfacility.ddd.user.exception.UserException;
import com.example.queuesystemfacility.ddd.user.ui.rest.request.UserDataResponse;
import com.example.queuesystemfacility.ddd.user.ui.rest.response.UpdateUserConfigurationRequest;
import com.example.queuesystemfacility.ddd.user.ui.rest.response.UserLoginRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserFacade userFacade;

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public UserDataResponse loginUser(@RequestBody @Valid UserLoginRequest request) throws UserException {
        UserDto userDto = userFacade.loginUser(request.getUserLogin(), request.getPassword());
        return UserDataResponse
                .builder()
                .userUUID(userDto.getUserUUID())
                .userLogin(userDto.getUserLogin())
                .displayServiceDeskName(userDto.getDisplayServiceDeskName())
                .pronouncedNumberPrefix(userDto.getPronouncedNumberPrefix())
                .pronouncedServiceDeskName(userDto.getPronouncedServiceDeskName())
                .build();
    }

    @PostMapping("/configuration")
    @ResponseStatus(HttpStatus.OK)
    public UserDataResponse loginUpdateUserConfiguration(@RequestBody @Valid UpdateUserConfigurationRequest request) throws UserException {
        UserDto userDto = userFacade.updateUserConfiguration(request.getUserUUID(),
                request.getDisplayServiceDeskName(),
                request.getPronouncedServiceDeskName(),
                request.getPronouncedNumberPrefix());
        return UserDataResponse
                .builder()
                .userUUID(userDto.getUserUUID())
                .userLogin(userDto.getUserLogin())
                .displayServiceDeskName(userDto.getDisplayServiceDeskName())
                .pronouncedNumberPrefix(userDto.getPronouncedNumberPrefix())
                .pronouncedServiceDeskName(userDto.getPronouncedServiceDeskName())
                .build();
    }
}
