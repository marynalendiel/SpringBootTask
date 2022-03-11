package com.example.springboottask.controller;

public class EntityResultNotFoundException extends RuntimeException {

    public EntityResultNotFoundException() {
    }

    public EntityResultNotFoundException(String message) {
        super(message);
    }

    public EntityResultNotFoundException(Throwable cause) {
        super(cause);
    }

    public EntityResultNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    protected EntityResultNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
