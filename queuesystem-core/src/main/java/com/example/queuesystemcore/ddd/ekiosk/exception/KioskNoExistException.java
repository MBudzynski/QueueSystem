package com.example.queuesystemcore.ddd.ekiosk.exception;

public class KioskNoExistException extends RuntimeException {

    public KioskNoExistException() {
        super();
    }

    public KioskNoExistException(String message) {
        super(message);
    }
}
