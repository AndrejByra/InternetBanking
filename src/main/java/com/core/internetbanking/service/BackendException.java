package com.core.internetbanking.service;

import org.springframework.http.HttpStatus;

public class BackendException extends RuntimeException {

    private final String message;
    private final HttpStatus httpStatus;

    public BackendException(String message, HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}