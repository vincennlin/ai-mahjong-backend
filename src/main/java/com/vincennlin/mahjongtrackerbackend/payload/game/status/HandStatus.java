package com.vincennlin.mahjongtrackerbackend.payload.game.status;

import com.vincennlin.mahjongtrackerbackend.payload.game.operation.HandOperation;
import lombok.Getter;

import java.util.Set;

@Getter
public enum HandStatus {
    READY_TO_DEAL(Set.of(HandOperation.DEAL_TILES)),
    FINISHED_DEALING(Set.of(HandOperation.BREAK_WALL)),
    FINISHED_BREAKING_WALL(Set.of(HandOperation.FOUL_HAND)),
    FINISHED_FOUL_HAND(Set.of(HandOperation.DISCARD_TILE)),
    IN_PROGRESS(Set.of(HandOperation.DRAW_TILE, HandOperation.CALL_CHOW, HandOperation.CALL_PONG, HandOperation.CALL_KONG, HandOperation.CALL_WIN)),
    FINISHED(Set.of());

    private final Set<HandOperation> acceptableOperations;

    HandStatus(Set<HandOperation> acceptableOperations) {
        this.acceptableOperations = acceptableOperations;
    }
}
