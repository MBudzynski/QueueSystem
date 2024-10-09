package com.example.queuesystemcore.infrastructure.message_broker;

import com.example.queuesystemcore.common.domain.QueueNumberDataDto;

public interface MessageBrokerClient {

    void sendNewQueueNumber(String queueName, QueueNumberDataDto object);
}
