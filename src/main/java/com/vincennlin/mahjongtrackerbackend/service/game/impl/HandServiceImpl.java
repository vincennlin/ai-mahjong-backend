package com.vincennlin.mahjongtrackerbackend.service.game.impl;

import com.vincennlin.mahjongtrackerbackend.constant.game.DefaultGameConstants;
import com.vincennlin.mahjongtrackerbackend.entity.game.Game;
import com.vincennlin.mahjongtrackerbackend.entity.game.Hand;
import com.vincennlin.mahjongtrackerbackend.entity.game.Round;
import com.vincennlin.mahjongtrackerbackend.entity.tile.PlayerTile;
import com.vincennlin.mahjongtrackerbackend.entity.tile.tilegroup.WallTileGroup;
import com.vincennlin.mahjongtrackerbackend.exception.ProcessException;
import com.vincennlin.mahjongtrackerbackend.exception.ResourceNotFoundException;
import com.vincennlin.mahjongtrackerbackend.mapper.game.BoardMapper;
import com.vincennlin.mahjongtrackerbackend.mapper.game.HandMapper;
import com.vincennlin.mahjongtrackerbackend.payload.game.dto.BoardDto;
import com.vincennlin.mahjongtrackerbackend.payload.game.dto.HandDto;
import com.vincennlin.mahjongtrackerbackend.payload.game.status.GameStatus;
import com.vincennlin.mahjongtrackerbackend.payload.game.status.HandStatus;
import com.vincennlin.mahjongtrackerbackend.payload.game.status.RoundStatus;
import com.vincennlin.mahjongtrackerbackend.repository.game.HandRepository;
import com.vincennlin.mahjongtrackerbackend.service.game.GameService;
import com.vincennlin.mahjongtrackerbackend.service.game.HandService;
import com.vincennlin.mahjongtrackerbackend.service.game.RoundService;
import com.vincennlin.mahjongtrackerbackend.service.game.TileService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

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

        Hand hand = getCurrentHandEntityByGameId(gameId);

        return handMapper.mapToDto(hand, hand.getRound().getRoundWind());
    }

    @Override
    public Hand getCurrentHandEntityByGameId(Long gameId) {

        return gameService.getGameEntityById(gameId).getCurrentHand();
    }

    @Override
    public Hand getHandEntityById(Long handId) {

        return handRepository.findById(handId).orElseThrow(() ->
            new ResourceNotFoundException("Hand", "id", handId));
    }

    @Override
    public BoardDto getBoardByGameId(Long gameId) {

        Hand hand = getCurrentHandEntityByGameId(gameId);

        tileService.sortHandGroupTiles(hand.getPlayerTiles());

        return boardMapper.mapToDto(hand);
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

        Hand hand = getCurrentHandEntityByGameId(gameId);

        if (hand.getStatus() != HandStatus.READY_TO_INITIALIZE_WALL_TILES) {
            throw new ProcessException(HttpStatus.BAD_REQUEST, hand.getStatus(), "Hand is not in ready to deal state");
        }

        WallTileGroup wallTileGroup = tileService.createWallTileGroup(hand);

        hand.setWallTileGroup(wallTileGroup);
        hand.setStatus(HandStatus.READY_TO_ROLL_DICE);

        Hand savedHand = handRepository.save(hand);

        return boardMapper.mapToDto(savedHand);
    }

    @Override
    public HandDto rollDice(Long gameId) {

        Hand hand = getCurrentHandEntityByGameId(gameId);

        if (hand.getStatus() != HandStatus.READY_TO_ROLL_DICE) {
            throw new ProcessException(HttpStatus.BAD_REQUEST, hand.getStatus(), "Hand is not in ready to roll dice state");
        }

        Integer diceNumber = (int) (((Math.random() * 6) + 1) + ((Math.random() * 6) + 1) + ((Math.random() * 6) + 1));

        hand.setDiceNumber(diceNumber);
        hand.setStatus(HandStatus.READY_TO_DEAL_TILES);

        Hand savedHand = handRepository.save(hand);

        return handMapper.mapToDto(savedHand, savedHand.getRound().getRoundWind());
    }

    @Override
    public BoardDto dealTiles(Long gameId) {

        Hand hand = getCurrentHandEntityByGameId(gameId);

        List<PlayerTile> playerTilesList = tileService.dealTiles(hand);

        hand.setPlayerTiles(playerTilesList);
        hand.setStatus(HandStatus.FINISHED_DEALING);

        Hand savedHand = handRepository.save(hand);

        return boardMapper.mapToDto(savedHand);
    }
}
