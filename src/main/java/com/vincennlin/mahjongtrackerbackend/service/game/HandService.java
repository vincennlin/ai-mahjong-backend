package com.vincennlin.mahjongtrackerbackend.service.game;

import com.vincennlin.mahjongtrackerbackend.entity.game.Hand;
import com.vincennlin.mahjongtrackerbackend.entity.tile.PlayerTile;
import com.vincennlin.mahjongtrackerbackend.payload.game.dto.BoardDto;
import com.vincennlin.mahjongtrackerbackend.payload.game.dto.HandDto;
import com.vincennlin.mahjongtrackerbackend.payload.game.dto.PlayerViewDto;

import java.util.List;

public interface HandService {

    HandDto getCurrentHandByGameId(Long gameId);

    Hand getCurrentHandEntityByGameId(Long gameId);

    Hand getHandEntityById(Long handId);

    BoardDto getBoardByGameId(Long gameId);

    PlayerViewDto getCurrentPlayerViewByGameId(Long gameId);

    List<PlayerTile> getPlayerTileEntityListByGameId(Long gameId);

    HandDto startNewHand(Long gameId);

    BoardDto initializeWallTiles(Long gameId);

    HandDto rollDice(Long gameId);

    BoardDto dealTiles(Long gameId);

    BoardDto breakWall(Long gameId);

    BoardDto initialFoulHand(Long gameId);
}
