package com.example.queuesystemfacility.ddd.queue.application;

import com.example.queuesystemfacility.common.application.ObservedQueueFacade;
import com.example.queuesystemfacility.common.application.UserFacade;
import com.example.queuesystemfacility.common.domain.UserDto;
import com.example.queuesystemfacility.ddd.queue.domain.ObservedQueue;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
class ObservedQueueFacadeImpl implements ObservedQueueFacade {

    private final ObservedUserQueueService observedUserQueueService;
    private final UserFacade userFacade;

    @Override
    public List<UUID> getUserQueueObserved(UUID userUUID) {
        UserDto user = findUser(userUUID);

        if(user == null) {
            return Collections.emptyList();
        }

        return observedUserQueueService
                .getObservedUserQueue(user.getUserId())
                .stream()
                .map(ObservedQueue::getQueueConfigurationUUID)
                .toList();
    }

    @Override
    public void saveUserQueueObserved(UUID userUUID, List<UUID> queueConfigurationUUIDs) {
        UserDto user = findUser(userUUID);

        if(user == null) {
            return;
        }

        observedUserQueueService.saveUserQueueObserved(user.getUserId(), queueConfigurationUUIDs);
    }

    private UserDto findUser(UUID userUUID) {
        try {
            return userFacade.findUserByUUID(userUUID);
        } catch (Exception e) {
            log.error("Error during search userUUID: " + userUUID.toString(), e.getMessage());
            return null;
        }
    }
}
