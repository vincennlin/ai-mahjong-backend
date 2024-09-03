package com.vincennlin.mahjongtrackerbackend.service.game;

import com.vincennlin.mahjongtrackerbackend.payload.game.CreateGameRequest;
import com.vincennlin.mahjongtrackerbackend.payload.game.GameDto;

public interface GameService {

    GameDto createGame(CreateGameRequest request);
}
