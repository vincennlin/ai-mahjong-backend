package com.vincennlin.mahjongtrackerbackend.payload.game.status;

import com.vincennlin.mahjongtrackerbackend.payload.game.operation.HandOperation;
import lombok.Getter;

import java.util.Set;

@Getter
public enum HandStatus implements ProcessStatus {
    READY_TO_INITIALIZE_WALL_TILES(Set.of(HandOperation.INITIALIZE_WALL_TILES)),
    READY_TO_ROLL_DICE(Set.of(HandOperation.ROLL_DICE)),
    READY_TO_DEAL_TILES(Set.of(HandOperation.DEAL_TILES)),
    FINISHED_DEALING(Set.of(HandOperation.BREAK_WALL)),
    FINISHED_BREAKING_WALL(Set.of(HandOperation.FOUL_HAND)),
    FINISHED_FOUL_HAND(Set.of(HandOperation.DISCARD_TILE)),
    WAITING_FOR_DISCARD(Set.of(HandOperation.DISCARD_TILE)),
    WAITING_FOR_CALL(Set.of(HandOperation.CALL_CHOW, HandOperation.CALL_PONG, HandOperation.CALL_KONG, HandOperation.CALL_WIN)),
    WAITING_FOR_DRAW(Set.of(HandOperation.DRAW_TILE)),
    IN_PROGRESS(Set.of(HandOperation.DRAW_TILE, HandOperation.CALL_CHOW, HandOperation.CALL_PONG, HandOperation.CALL_KONG, HandOperation.CALL_WIN)),
    FINISHED(Set.of());

    private final Set<HandOperation> acceptableOperations;

    HandStatus(Set<HandOperation> acceptableOperations) {
        this.acceptableOperations = acceptableOperations;
    }
}
