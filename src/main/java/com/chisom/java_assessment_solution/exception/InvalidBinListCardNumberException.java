package com.chisom.java_assessment_solution.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidBinListCardNumberException extends RuntimeException{

    public InvalidBinListCardNumberException(String message) {
        super(message);
    }
}
