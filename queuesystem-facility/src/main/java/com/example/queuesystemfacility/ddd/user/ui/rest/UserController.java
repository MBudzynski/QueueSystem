package com.example.queuesystemfacility.ddd.user.ui.rest;

import com.example.queuesystemfacility.common.application.UserFacade;
import com.example.queuesystemfacility.common.domain.UserDto;
import com.example.queuesystemfacility.ddd.user.exception.UserException;
import com.example.queuesystemfacility.ddd.user.ui.rest.request.UpdateUserConfigurationRequest;
import com.example.queuesystemfacility.ddd.user.ui.rest.response.UserDataResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserFacade userFacade;

    @GetMapping("/configuration")
    @ResponseStatus(HttpStatus.OK)
    public UserDataResponse getUserConfiguration(Principal principal) throws UserException {
        UserDto userDto = userFacade.getUserConfiguration(principal.getName());
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
    public UserDataResponse updateUserConfiguration(@RequestBody @Valid UpdateUserConfigurationRequest request) {
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
