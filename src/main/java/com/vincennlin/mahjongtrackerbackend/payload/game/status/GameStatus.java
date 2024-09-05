package com.vincennlin.mahjongtrackerbackend.payload.game.status;

import com.vincennlin.mahjongtrackerbackend.payload.game.operation.GameOperation;
import lombok.Getter;

import java.util.Set;

@Getter
public enum GameStatus implements ProcessStatus {
    WAITING_FOR_PLAYERS(Set.of(GameOperation.ADD_PLAYER)),
    READY_TO_START(Set.of(GameOperation.PICK_SEATS)),
    FINISHED_PICKING_SEATS(Set.of(GameOperation.DECIDE_EAST_PLAYER)),
    READY_TO_START_NEW_ROUND(Set.of(GameOperation.START_NEW_HAND)),
    READY_TO_START_NEW_HAND(Set.of(GameOperation.START_NEW_HAND)),
    IN_PROGRESS(Set.of(GameOperation.FINISH_ROUND, GameOperation.FINISH_GAME)),
    FINISHED_ROUND(Set.of(GameOperation.START_NEW_HAND, GameOperation.FINISH_GAME)),
    FINISHED(Set.of());

    private final Set<GameOperation> acceptableOperations;

    GameStatus(Set<GameOperation> acceptableOperations) {
        this.acceptableOperations = acceptableOperations;
    }
}
