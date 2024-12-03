package com.example.queuesystemfacility.ddd.user.exception;

public class UserException extends RuntimeException {

    public UserException() {
    }

    public UserException(String message) {
        super(message);
    }
}
