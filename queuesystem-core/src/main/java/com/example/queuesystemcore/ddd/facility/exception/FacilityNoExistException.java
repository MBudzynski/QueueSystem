package com.example.queuesystemcore.ddd.facility.exception;

public class FacilityNoExistException extends RuntimeException {
    public FacilityNoExistException() {
        super();
    }
    public FacilityNoExistException(String message) {
        super(message);
    }
}
