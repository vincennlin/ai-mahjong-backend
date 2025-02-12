package com.vincennlin.aimahjongbackend.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class InternalGameError extends InternalError{

    private HttpStatus httpStatus;
    private String message;

    public InternalGameError(HttpStatus status, String message) {
        this.httpStatus = status;
        this.message = message;
    }
}
