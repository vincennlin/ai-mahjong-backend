package com.vincennlin.mahjongtrackerbackend.service.game;

import com.vincennlin.mahjongtrackerbackend.entity.game.Hand;
import com.vincennlin.mahjongtrackerbackend.payload.game.dto.BoardDto;
import com.vincennlin.mahjongtrackerbackend.payload.game.dto.HandDto;

public interface HandService {

    HandDto getCurrentHandByGameId(Long gameId);

    Hand getCurrentHandEntityByGameId(Long gameId);

    Hand getHandEntityById(Long handId);

    HandDto startNewHand(Long gameId);

    BoardDto initializeWallTiles(Long gameId);

    HandDto rollDice(Long gameId);

    BoardDto dealTiles(Long gameId);
}
