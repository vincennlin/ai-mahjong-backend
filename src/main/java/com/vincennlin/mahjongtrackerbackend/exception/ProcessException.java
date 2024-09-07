package com.vincennlin.mahjongtrackerbackend.exception;

import com.vincennlin.mahjongtrackerbackend.payload.game.status.ProcessStatus;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ProcessException extends RuntimeException {

    private HttpStatus httpStatus;
    private ProcessStatus processStatus;
    private String message;

    public ProcessException(HttpStatus httpStatus, ProcessStatus processStatus, String message) {
        this.httpStatus = httpStatus;
        this.processStatus = processStatus;
        this.message = message;
    }
}
