package com.vincennlin.mahjongtrackerbackend.payload.game.gamestatus;

public enum GameStatus implements AbstractGameStatus{
    WAITING_FOR_PLAYERS,
    READY_TO_START,
    PICKING_SEATS,
    FINISHED_PICKING_SEATS,
    FINISHED_DECIDING_EAST_PLAYER,
    IN_PROGRESS,
    FINISHED
}
