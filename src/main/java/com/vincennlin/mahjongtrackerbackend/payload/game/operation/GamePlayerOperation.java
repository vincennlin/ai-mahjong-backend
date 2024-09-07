package com.vincennlin.mahjongtrackerbackend.payload.game.operation;

import com.vincennlin.mahjongtrackerbackend.payload.game.status.GamePlayerStatus;

public enum GamePlayerOperation {
    CALL_FOR_CHOW(GamePlayerStatus.THINKING_FOR_DISCARD),
    CALL_FOR_PONG(GamePlayerStatus.THINKING_FOR_DISCARD),
    CALL_FOR_EXPOSED_KONG(GamePlayerStatus.THINKING_FOR_DISCARD),
    CANCEL(GamePlayerStatus.WAITING),

    DRAW_TILE(GamePlayerStatus.THINKING_FOR_DISCARD),

    DISCARD(GamePlayerStatus.WAITING),
    CALL_FOR_CONCEALED_KONG(GamePlayerStatus.THINKING_FOR_DISCARD),
    CALL_FOR_ADDED_KONG(GamePlayerStatus.THINKING_FOR_DISCARD),

    CALL_FOR_WIN(GamePlayerStatus.WON),
    CALL_FOR_SELF_DRAW(GamePlayerStatus.SELF_DRAWN_WIN);

    private final GamePlayerStatus nextStatus;

    GamePlayerOperation(GamePlayerStatus nextStatus) {
        this.nextStatus = nextStatus;
    }
}
