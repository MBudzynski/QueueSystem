package com.example.queuesystemcore.ddd.kiosk.exception;

public class KioskNoExistException extends RuntimeException {

    public KioskNoExistException() {
        super();
    }

    public KioskNoExistException(String message) {
        super(message);
    }
}
