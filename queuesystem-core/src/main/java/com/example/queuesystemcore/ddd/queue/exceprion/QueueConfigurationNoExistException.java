package com.example.queuesystemcore.ddd.queue.exceprion;

public class QueueConfigurationNoExistException extends RuntimeException{

    public QueueConfigurationNoExistException() {
        super();
    }

    public QueueConfigurationNoExistException(String message) {
        super(message);
    }
}
