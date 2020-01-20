package com.chisom.java_assessment_solution.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidPageException extends RuntimeException{

    public InvalidPageException(String message) {
        super(message);
    }
}
