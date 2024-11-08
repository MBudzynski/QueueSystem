package com.example.queuesystemfacility.ddd.queue.application;

import com.example.queuesystemfacility.common.application.ObservedQueueFacade;
import com.example.queuesystemfacility.common.application.UserFacade;
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
    public List<UUID> getObservedUserQueue(UUID userUUID) {
        Long userId = findUserId(userUUID);

        if(userId == null) {
            return Collections.emptyList();
        }

        return observedUserQueueService
                .getObservedUserQueue(userId)
                .stream()
                .map(ObservedQueue::getQueueConfigurationUUID)
                .toList();
    }

    @Override
    public void saveUserObservedQueue(UUID userUUID, List<UUID> queueConfigurationUUIDs) {
        Long userId = findUserId(userUUID);

        if(userId == null) {
            return;
        }

        observedUserQueueService.saveUserObservedQueue(userId, queueConfigurationUUIDs);
    }

    private Long findUserId(UUID userUUID) {
        try {
            return userFacade.findUserIdByUUID(userUUID);
        } catch (Exception e) {
            log.error("Error during search userUUID: " + userUUID.toString(), e.getMessage());
            return null;
        }
    }
}
