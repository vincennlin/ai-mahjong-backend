package com.vincennlin.aimahjongbackend.service.game;

import com.vincennlin.aimahjongbackend.entity.game.Hand;
import com.vincennlin.aimahjongbackend.entity.tile.PlayerTile;
import com.vincennlin.aimahjongbackend.payload.game.dto.BoardDto;
import com.vincennlin.aimahjongbackend.payload.game.dto.HandDto;
import com.vincennlin.aimahjongbackend.payload.game.dto.PlayerViewDto;
import com.vincennlin.aimahjongbackend.payload.game.request.ai.DiscardAdviceResponse;

import java.util.List;

public interface HandService {

    HandDto getCurrentHandByGameId(Long gameId);

    Hand getCurrentHandEntityByGameId(Long gameId);

    Hand getHandEntityById(Long handId);

    BoardDto getBoardByGameId(Long gameId);

    PlayerViewDto getPlayerViewByGameIdAndGamePlayerId(Long gameId, Long gamePlayerId);

    PlayerViewDto getCurrentPlayerViewByGameId(Long gameId);

    List<PlayerTile> getPlayerTileEntityListByGameId(Long gameId);

    HandDto startNewHand(Long gameId);

    BoardDto initializeWallTiles(Long gameId);

    HandDto rollDice(Long gameId);

    BoardDto dealTiles(Long gameId);

    BoardDto breakWall(Long gameId);

    PlayerViewDto initialFoulHand(Long gameId);

    PlayerViewDto discardTile(Long gameId, Long boardTileId);

    DiscardAdviceResponse generateDiscardAdvice(Long gameId);

    PlayerViewDto cancelForCall(Long gameId);

    PlayerViewDto drawTile(Long gameId);

    PlayerViewDto pongTile(Long gameId);

    PlayerViewDto chowTile(Long gameId, int combinationIndex);

    PlayerViewDto exposeKongTile(Long gameId);

    PlayerViewDto concealKongTile(Long gameId, int combinationIndex);
}
