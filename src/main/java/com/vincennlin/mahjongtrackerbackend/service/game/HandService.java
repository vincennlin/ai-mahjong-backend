package com.vincennlin.mahjongtrackerbackend.service.game;

import com.vincennlin.mahjongtrackerbackend.payload.game.dto.HandDto;

public interface HandService {

    HandDto getCurrentHandByGameId(Long gameId);

    HandDto startNewHand(Long gameId);

    int rollDice();
}
