package com.chisom.java_assessment_solution.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler
    public final ResponseEntity<Object> handleInvalidBinkListCardNumberException(InvalidBinListCardNumberException ex, WebRequest request) {
        InvalidBinkListCardNumberResponse binkListCardNumberResponse = new InvalidBinkListCardNumberResponse(ex.getMessage());
        return new ResponseEntity<>(binkListCardNumberResponse, HttpStatus.BAD_REQUEST);
    }
}
