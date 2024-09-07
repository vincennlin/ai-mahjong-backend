package com.vincennlin.mahjongtrackerbackend.payload.game.status;

import com.vincennlin.mahjongtrackerbackend.payload.game.operation.GamePlayerOperation;
import lombok.Getter;

import java.util.Set;

@Getter
public enum GamePlayerStatus implements ProcessStatus {
    WAITING(),

    THINKING_FOR_CALL(GamePlayerOperation.CALL_FOR_CHOW, GamePlayerOperation.CALL_FOR_PONG, GamePlayerOperation.CALL_FOR_EXPOSED_KONG, GamePlayerOperation.CANCEL),
    THINKING_FOR_WIN(GamePlayerOperation.CALL_FOR_WIN, GamePlayerOperation.CANCEL),

    ABLE_TO_DRAW_TILE(GamePlayerOperation.DRAW_TILE),

    THINKING_FOR_DISCARD(GamePlayerOperation.CALL_FOR_CONCEALED_KONG, GamePlayerOperation.CALL_FOR_ADDED_KONG, GamePlayerOperation.CALL_FOR_SELF_DRAW, GamePlayerOperation.DISCARD),

    WON(),
    SELF_DRAWN_WIN(),
    CHUCKED(),
    BEING_SELF_DRAWN(),
    SAFE();

    private final Set<GamePlayerOperation> acceptableOperations;

    GamePlayerStatus(GamePlayerOperation... acceptableOperations) {
        this.acceptableOperations = Set.of(acceptableOperations);
    }
}
