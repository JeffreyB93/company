package com.example.company.exception;

import lombok.Getter;

@Getter
public class AtiApiException extends RuntimeException {
    private final int statusCode;

    public AtiApiException(String message) {
        super(message);
        this.statusCode = 500;
    }

    public AtiApiException(String message, int statusCode) {
        super(message);
        this.statusCode = statusCode;
    }

    public AtiApiException(String message, Throwable cause) {
        super(message, cause);
        this.statusCode = 500;
    }

    public AtiApiException(String message, int statusCode, Throwable cause) {
        super(message, cause);
        this.statusCode = statusCode;
    }
}
