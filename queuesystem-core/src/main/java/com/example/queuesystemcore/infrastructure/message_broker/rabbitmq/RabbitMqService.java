package com.example.queuesystemcore.infrastructure.message_broker.rabbitmq;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
class RabbitMqService implements RabbitMqClient {

    private final RabbitTemplate rabbitTemplate;

    @Override
    public void sendMessage(String queueName, Object message) {

        if (queueName == null || queueName.isBlank()) {
            throw new IllegalArgumentException("RabbitMQ queue name must not be blank");
        }

        try {
            rabbitTemplate.convertAndSend("", queueName, message);
            log.debug("Sent message to RabbitMQ queue '{}'", queueName);
        } catch (AmqpException exception) {
            log.error("Failed to publish message to RabbitMQ queue '{}'", queueName, exception);
            throw exception;
        }
    }
}
