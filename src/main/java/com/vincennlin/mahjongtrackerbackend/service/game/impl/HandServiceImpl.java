package com.vincennlin.mahjongtrackerbackend.service.game.impl;

import com.vincennlin.mahjongtrackerbackend.constant.game.DefaultGameConstants;
import com.vincennlin.mahjongtrackerbackend.entity.game.Game;
import com.vincennlin.mahjongtrackerbackend.entity.game.Hand;
import com.vincennlin.mahjongtrackerbackend.entity.game.Round;
import com.vincennlin.mahjongtrackerbackend.entity.tile.BoardTile;
import com.vincennlin.mahjongtrackerbackend.entity.tile.tilegroup.WallTileGroup;
import com.vincennlin.mahjongtrackerbackend.exception.InternalGameError;
import com.vincennlin.mahjongtrackerbackend.exception.ProcessException;
import com.vincennlin.mahjongtrackerbackend.mapper.game.BoardMapper;
import com.vincennlin.mahjongtrackerbackend.mapper.game.HandMapper;
import com.vincennlin.mahjongtrackerbackend.payload.game.dto.BoardDto;
import com.vincennlin.mahjongtrackerbackend.payload.game.dto.HandDto;
import com.vincennlin.mahjongtrackerbackend.payload.game.status.GameStatus;
import com.vincennlin.mahjongtrackerbackend.payload.game.status.HandStatus;
import com.vincennlin.mahjongtrackerbackend.payload.game.status.RoundStatus;
import com.vincennlin.mahjongtrackerbackend.payload.tile.impl.Tile;
import com.vincennlin.mahjongtrackerbackend.repository.game.HandRepository;
import com.vincennlin.mahjongtrackerbackend.repository.game.WallTileGroupRepository;
import com.vincennlin.mahjongtrackerbackend.service.game.GameService;
import com.vincennlin.mahjongtrackerbackend.service.game.HandService;
import com.vincennlin.mahjongtrackerbackend.service.game.RoundService;
import com.vincennlin.mahjongtrackerbackend.service.game.TileService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@AllArgsConstructor
@Service
public class HandServiceImpl implements HandService {

    private final HandMapper handMapper;
    private final BoardMapper boardMapper;

    private final GameService gameService;
    private final RoundService roundService;
    private final TileService tileService;

    private final HandRepository handRepository;

    @Override
    public HandDto getCurrentHandByGameId(Long gameId) {

        Game game = gameService.getGameEntityById(gameId);

        Round round = game.getCurrentRound();

        Hand hand = round.getCurrentHand();

        return handMapper.mapToDto(hand, round.getRoundWind());
    }

    @Override
    public HandDto startNewHand(Long gameId) {

        Game game = gameService.getGameEntityById(gameId);

        Round round = null;

        if (game.getStatus() == GameStatus.READY_TO_START_NEW_ROUND) {
            round = roundService.startNewRoundAndGetEntity(game);
        } else if (game.getStatus() == GameStatus.READY_TO_START_NEW_HAND) {
            round = game.getCurrentRound();
        } else {
            throw new ProcessException(HttpStatus.BAD_REQUEST, game.getStatus(), "Game is not ready to start a new hand");
        }

        Hand hand = new Hand(round, round.getNextDealer(), round.getNextPrevailingWind());

        Hand newHand = handRepository.save(hand);

        round.setStatus(RoundStatus.IN_PROGRESS);
        roundService.saveRound(round);

        game.setStatus(GameStatus.IN_PROGRESS);
        gameService.saveGame(game);

        return handMapper.mapToDto(newHand, round.getRoundWind());
    }

    @Override
    public BoardDto initializeWallTiles(Long gameId) {

        Game game = gameService.getGameEntityById(gameId);

        Hand hand = game.getCurrentHand();

        if (hand.getStatus() != HandStatus.READY_TO_DEAL) {
            throw new ProcessException(HttpStatus.BAD_REQUEST, hand.getStatus(), "Hand is not in ready to deal state");
        }

        WallTileGroup wallTileGroup = tileService.createWallTileGroup(hand);

        hand.setWallTileGroup(wallTileGroup);
        hand.setStatus(HandStatus.FINISHED_DEALING);

        Hand savedHand = handRepository.save(hand);

        return boardMapper.mapToDto(savedHand, new ArrayList<>());
    }

    @Override
    public BoardDto dealTiles(Long gameId) {
        return null;
    }

    @Override
    public int rollDice() {
        int diceCount = DefaultGameConstants.DEFAULT_DICE_COUNT;
        return (int) ((Math.random() * 6) + 1) * diceCount;
    }
}
