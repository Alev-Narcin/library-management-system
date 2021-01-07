package com.alevnarcin.librarymanagementsystem.exception.handler;

import com.alevnarcin.librarymanagementsystem.exception.response.ExceptionResponse;
import com.alevnarcin.librarymanagementsystem.exception.entity.ExceptionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(value = {ExceptionResponse.class})
    public ResponseEntity<Object> handleApiRequestException(ExceptionResponse e) {

        // 1.create payload containing exception details(istisna ve ayrıntı içerir)
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;

        ExceptionModel exceptionModel = new ExceptionModel(
                e.getMessage(),
                badRequest,
                ZonedDateTime.now(ZoneId.of("Z"))
        );
        // 2.Return response entity
        return new ResponseEntity<>(exceptionModel, badRequest);
    }
}
