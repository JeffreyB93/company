package com.example.company.exception;

public class AtiResourceNotFoundException extends AtiApiException {
    public AtiResourceNotFoundException(String message) {
        super(message, 404);
    }

    public AtiResourceNotFoundException(String message, Throwable cause) {
        super(message, 404, cause);
    }
}
