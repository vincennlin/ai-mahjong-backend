package com.vincennlin.mahjongtrackerbackend.service.game;

import com.vincennlin.mahjongtrackerbackend.payload.game.CreatePlayerRequest;
import com.vincennlin.mahjongtrackerbackend.payload.game.PlayerDto;

public interface PlayerService {

    PlayerDto createPlayer(CreatePlayerRequest request);
}
