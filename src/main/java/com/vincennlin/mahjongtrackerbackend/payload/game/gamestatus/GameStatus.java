package com.vincennlin.mahjongtrackerbackend.payload.game.gamestatus;

public enum GameStatus {
    WAITING_FOR_PLAYERS,
    READY_TO_START,
    PICKING_SEATS,
    FINISHED_PICKING_SEATS,
    READY_TO_START_NEW_ROUND,
    READY_TO_START_NEW_HAND,
    IN_PROGRESS,
    FINISHED_ROUND,
    FINISHED
}
