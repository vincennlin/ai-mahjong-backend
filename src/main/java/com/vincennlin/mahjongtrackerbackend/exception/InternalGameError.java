package com.vincennlin.mahjongtrackerbackend.exception;

import com.vincennlin.mahjongtrackerbackend.payload.game.status.ProcessStatus;
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
