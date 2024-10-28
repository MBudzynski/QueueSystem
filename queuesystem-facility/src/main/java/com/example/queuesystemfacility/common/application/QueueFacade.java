package com.example.queuesystemfacility.common.application;

import com.example.queuesystemfacility.common.domain.QueueNumberDto;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.UUID;

public interface QueueFacade {

    void deleteQueueNumber(UUID queueUUID);

    void delayQueueNumber(@NotNull UUID queueUUID);

    QueueNumberDto bringNextNumber(@NotNull UUID userUUID);

    List<QueueNumberDto> showAllNumbers(UUID userUUID);
}
