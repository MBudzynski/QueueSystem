package com.example.queuesystemcore.infrastructure.message_broker;

import com.example.queuesystemcore.common.domain.QueueDto;

public interface MessageBrokerClient {

    void sendNewQueueNumber(String queueName, QueueDto object);
}
