package com.vincennlin.aimahjongbackend.payload.game.status;

import com.vincennlin.aimahjongbackend.entity.game.GamePlayer;
import com.vincennlin.aimahjongbackend.entity.game.Hand;
import com.vincennlin.aimahjongbackend.payload.game.operation.GamePlayerOperation;

import java.util.HashSet;
import java.util.Set;

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

    public Set<GamePlayerOperation> getAcceptableOperations(Hand hand, GamePlayer gamePlayer) {
        Set<GamePlayerOperation> returnOperations = new HashSet<>();
        for (GamePlayerOperation operation : acceptableOperations) {
            if (operation.canOperate(hand, gamePlayer)) {
                returnOperations.add(operation);
            }
        }
        return returnOperations;
    }
}
