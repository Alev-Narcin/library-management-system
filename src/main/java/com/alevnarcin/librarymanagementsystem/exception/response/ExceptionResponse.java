package com.alevnarcin.librarymanagementsystem.exception.response;

public class ExceptionResponse extends RuntimeException {

    public ExceptionResponse(String message) {
        super(message);
    }

    public ExceptionResponse(String message, Throwable cause) {
        super(message, cause);
    }
}
