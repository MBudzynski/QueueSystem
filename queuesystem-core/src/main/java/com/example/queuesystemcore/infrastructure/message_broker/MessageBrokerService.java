package com.example.queuesystemcore.infrastructure.message_broker;

import com.example.queuesystemcore.common.domain.QueueDto;
import com.example.queuesystemcore.infrastructure.message_broker.rabbitmq.RabbitMqClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
class MessageBrokerService implements MessageBrokerClient {

    private final RabbitMqClient rabbitMqClient;

    public void sendNewQueueNumber(String queueName, QueueDto object) {
        rabbitMqClient.sendMessage(queueName, object);
    }

}
