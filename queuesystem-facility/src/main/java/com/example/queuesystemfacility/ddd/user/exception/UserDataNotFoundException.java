package com.example.queuesystemfacility.ddd.user.exception;

public class UserDataNotFoundException extends RuntimeException{

    public UserDataNotFoundException(String message) {
        super(message);
    }
}
