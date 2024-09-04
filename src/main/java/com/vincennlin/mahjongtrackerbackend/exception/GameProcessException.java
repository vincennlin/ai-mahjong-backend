package com.vincennlin.mahjongtrackerbackend.exception;

import com.vincennlin.mahjongtrackerbackend.payload.game.status.GameStatus;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class GameProcessException extends RuntimeException {

    private HttpStatus httpStatus;
    private GameStatus gameStatus;
    private String message;

    public GameProcessException(HttpStatus httpStatus, GameStatus gameStatus, String message) {
        this.httpStatus = httpStatus;
        this.gameStatus = gameStatus;
        this.message = message;
    }
}
