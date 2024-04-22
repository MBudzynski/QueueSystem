package com.example.queuesystemcore.ddd.localization.exception;

public class LocalizationNoExistException extends RuntimeException {
    public LocalizationNoExistException() {
        super();
    }
    public LocalizationNoExistException(String message) {
        super(message);
    }
}
