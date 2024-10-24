package com.example.queuesystemfacility.ddd.queue.application;

import com.example.queuesystemfacility.common.application.QueueFacade;
import com.example.queuesystemfacility.common.application.UserFacade;
import com.example.queuesystemfacility.common.domain.QueueNumberDto;
import com.example.queuesystemfacility.ddd.queue.domain.Queue;
import java.util.Collections;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
class QueueFacadeImpl implements QueueFacade {

    private final QueueService queueService;
    private final UserFacade userFacade;

    public void deleteQueueNumber(UUID queueUUID) {
        queueService.deleteQueueNumber(queueUUID);
    }

    @Override
    public void delayQueueNumber(UUID queueUUID) {
        queueService.delayQueueNumber(queueUUID);
    }

    @Override
    public QueueNumberDto bringNextNumber(UUID userUUID) {

        Long userId = findUserId(userUUID);

        if(userId == null) {
            return null;
        }

        return Optional.ofNullable(queueService.bringNextNumber(userId))
                .map(Queue::mutateTo)
                .orElse(null);
    }

    @Override
    public List<QueueNumberDto> showAllNumbers(UUID userUUID) {
        Long userId = findUserId(userUUID);

        if(userId == null) {
            return Collections.emptyList();
        }

        return queueService.showAllNumbers(userId)
                .stream()
                .map(Queue::mutateTo)
                .toList();
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
