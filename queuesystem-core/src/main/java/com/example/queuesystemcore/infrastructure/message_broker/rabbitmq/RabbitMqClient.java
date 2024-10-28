package com.example.queuesystemcore.infrastructure.message_broker.rabbitmq;

public interface RabbitMqClient {
    void sendMessage(String queueName, Object message);
}
