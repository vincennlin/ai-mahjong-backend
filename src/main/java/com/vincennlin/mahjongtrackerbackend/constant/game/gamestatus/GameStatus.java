package com.vincennlin.mahjongtrackerbackend.constant.game.gamestatus;

public enum GameStatus implements AbstractGameStatus{
    WAITING_FOR_PLAYERS,
    READY_TO_START,
    PICKING_SEATS,
    IN_PROGRESS,
    FINISHED
}
