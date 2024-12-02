package com.example.queuesystemfacility.ddd.queue.application;

import com.example.queuesystemfacility.common.application.QueueFacade;
import com.example.queuesystemfacility.common.application.UserFacade;
import com.example.queuesystemfacility.common.domain.UserDto;
import com.example.queuesystemfacility.ddd.queue.domain.Queue;
import java.util.Collections;

import com.example.queuesystemfacility.ddd.queue.domain.QueueNumberDto;
import com.example.queuesystemfacility.ddd.queue.infrastructure.web_socket.WebSocketClientService;
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
    private final WebSocketClientService webSocketClientService;

    public void deleteQueueNumber(UUID queueUUID) {
        queueService.deleteQueueNumber(queueUUID);
    }

    @Override
    public void delayQueueNumber(UUID queueUUID) {
        queueService.delayQueueNumber(queueUUID);
    }

    @Override
    public com.example.queuesystemfacility.common.domain.QueueNumberDto bringNextNumber(UUID userUUID) {

        UserDto user = findUserId(userUUID);

        if(user == null) {
            return null;
        }

        return Optional.ofNullable(queueService.bringNextNumber(user.getUserId()))
                .map(queue -> {
                    webSocketClientService.sendMessageToApp(user.getDisplayDeviceIp(), new QueueNumberDto(
                            user.getPronouncedNumberPrefix(),
                            queue.getSign(),
                            queue.getNum().toString(),
                            queue.getFullNumber(),
                            user.getDisplayServiceDeskName(),
                            user.getPronouncedServiceDeskName()));
                    return queue.mutateTo();
                })
                .orElse(null);
    }

    @Override
    public List<com.example.queuesystemfacility.common.domain.QueueNumberDto> showAllNumbers(UUID userUUID) {
        UserDto user = findUserId(userUUID);

        if(user == null) {
            return Collections.emptyList();
        }

        return queueService.showAllNumbers(user.getUserId())
                .stream()
                .map(Queue::mutateTo)
                .toList();
    }

    private UserDto findUserId(UUID userUUID) {
        try {
            return userFacade.findUserByUUID(userUUID);
        } catch (Exception e) {
            log.error("Error during search userUUID: " + userUUID.toString(), e.getMessage());
            return null;
        }
    }
}
