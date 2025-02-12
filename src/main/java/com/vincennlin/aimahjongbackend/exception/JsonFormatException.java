package com.vincennlin.aimahjongbackend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class JsonFormatException extends RuntimeException {

    String message;
    String details;

    public JsonFormatException(String message, String details) {
        super(message);
        this.message = message;
        this.details = details;
    }
}
