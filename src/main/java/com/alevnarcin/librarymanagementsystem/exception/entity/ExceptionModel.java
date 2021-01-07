package com.alevnarcin.librarymanagementsystem.exception.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import java.time.ZonedDateTime;


@Getter
@Setter
public class ExceptionModel {

    private final String message;
    private final HttpStatus httpStatus;
    private final ZonedDateTime timestamp;

    public ExceptionModel(String message,
                          HttpStatus httpStatus,
                          ZonedDateTime timestamp) {
        this.message = message;

        this.httpStatus = httpStatus;
        this.timestamp = timestamp;
    }
}
