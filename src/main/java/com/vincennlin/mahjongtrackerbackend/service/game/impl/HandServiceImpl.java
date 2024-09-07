package com.vincennlin.mahjongtrackerbackend.service.game.impl;

import com.vincennlin.mahjongtrackerbackend.constant.game.DefaultGameConstants;
import com.vincennlin.mahjongtrackerbackend.entity.game.*;
import com.vincennlin.mahjongtrackerbackend.entity.tile.BoardTile;
import com.vincennlin.mahjongtrackerbackend.entity.tile.PlayerTile;
import com.vincennlin.mahjongtrackerbackend.entity.tile.tilegroup.WallTileGroup;
import com.vincennlin.mahjongtrackerbackend.exception.ProcessException;
import com.vincennlin.mahjongtrackerbackend.exception.ResourceNotFoundException;
import com.vincennlin.mahjongtrackerbackend.mapper.game.BoardMapper;
import com.vincennlin.mahjongtrackerbackend.mapper.game.HandMapper;
import com.vincennlin.mahjongtrackerbackend.payload.game.dto.BoardDto;
import com.vincennlin.mahjongtrackerbackend.payload.game.dto.GameDto;
import com.vincennlin.mahjongtrackerbackend.payload.game.dto.HandDto;
import com.vincennlin.mahjongtrackerbackend.payload.game.dto.PlayerViewDto;
import com.vincennlin.mahjongtrackerbackend.payload.game.operation.GamePlayerOperation;
import com.vincennlin.mahjongtrackerbackend.payload.game.status.GamePlayerStatus;
import com.vincennlin.mahjongtrackerbackend.payload.game.status.GameStatus;
import com.vincennlin.mahjongtrackerbackend.payload.game.status.HandStatus;
import com.vincennlin.mahjongtrackerbackend.payload.game.status.RoundStatus;
import com.vincennlin.mahjongtrackerbackend.repository.game.HandRepository;
import com.vincennlin.mahjongtrackerbackend.service.game.*;
import com.vincennlin.mahjongtrackerbackend.service.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class HandServiceImpl implements HandService {

    private final HandMapper handMapper;
    private final BoardMapper boardMapper;

    private final UserService  userService;
    private final GameService gameService;
    private final RoundService roundService;
    private final TileService tileService;
    private final GamePlayerService gamePlayerService;
    private final OperationService operationService;

    private final HandRepository handRepository;

    @Override
    public HandDto getCurrentHandByGameId(Long gameId) {

        Hand hand = getCurrentHandEntityByGameId(gameId);

        return handMapper.mapToDto(hand, hand.getRound().getRoundWind());
    }

    @Override
    public Hand getCurrentHandEntityByGameId(Long gameId) {

        Game game = gameService.getGameEntityById(gameId);

        return game.getCurrentHand();
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
    public PlayerViewDto getPlayerViewByGameIdAndGamePlayerId(Long gameId, Long gamePlayerId) {

        Hand hand = getCurrentHandEntityByGameId(gameId);

        GamePlayer gamePlayer = getGamePlayerByGameAndGamePlayerId(hand.getRound().getGame(), gamePlayerId);

        return boardMapper.mapToPlayerViewDto(hand, gamePlayer);
    }

    @Override
    public PlayerViewDto getCurrentPlayerViewByGameId(Long gameId) {

        Hand hand = getCurrentHandEntityByGameId(gameId);

        GamePlayer currentGamePlayer = hand.getGamePlayerByUserId(userService.getCurrentUserId());

        return boardMapper.mapToPlayerViewDto(hand, currentGamePlayer);
    }

    @Override
    public List<PlayerTile> getPlayerTileEntityListByGameId(Long gameId) {
        return getCurrentHandEntityByGameId(gameId).getPlayerTiles();
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
        round.getHands().add(newHand);
        Round savedRound = roundService.saveRound(round);

        game.setStatus(GameStatus.IN_PROGRESS);
        game.getRounds().add(savedRound);
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

        if (hand.getStatus() != HandStatus.READY_TO_DEAL_TILES) {
            throw new ProcessException(HttpStatus.BAD_REQUEST, hand.getStatus(), "Hand is not in ready to deal state");
        }

        tileService.reorderWallTiles(tileService.getFirstTileIndex(hand.getDiceNumber()), hand.getWallTileGroup());

        List<PlayerTile> playerTilesList = tileService.dealTiles(hand);

        hand.setPlayerTiles(playerTilesList);
        hand.setStatus(HandStatus.FINISHED_DEALING);

        Hand savedHand = handRepository.save(hand);

        return boardMapper.mapToDto(savedHand);
    }

    @Override
    public BoardDto breakWall(Long gameId) {

        Hand hand = getCurrentHandEntityByGameId(gameId);

        if (hand.getStatus() != HandStatus.FINISHED_DEALING) {
            throw new ProcessException(HttpStatus.BAD_REQUEST, hand.getStatus(), "Hand is not in finished dealing state");
        }

        tileService.drawTile(hand.getDealer().getPlayerTile(), hand.getWallTileGroup());

        hand.setStatus(HandStatus.FINISHED_BREAKING_WALL);

        return boardMapper.mapToDto(handRepository.save(hand));
    }

    @Override
    public BoardDto initialFoulHand(Long gameId) {

        Hand hand = getCurrentHandEntityByGameId(gameId);

        if (hand.getStatus() != HandStatus.FINISHED_BREAKING_WALL) {
            throw new ProcessException(HttpStatus.BAD_REQUEST, hand.getStatus(), "Hand is not in finished breaking wall state");
        }

        GamePlayer currentPlayer = getCurrentHandEntityByGameId(gameId).getDealer();

        while (hand.getPlayerTiles().stream().anyMatch(
                playerTile -> playerTile.getHandTiles().containsFlowerTile())) {
            for (int i = 0; i < DefaultGameConstants.DEFAULT_PLAYER_COUNT; i++) {
                PlayerTile playerTile = currentPlayer.getPlayerTile();
                if (playerTile.getHandTiles().containsFlowerTile()) {
                    playerTile.getHandTiles().sortHandTiles();
                    tileService.initialFoulHand(playerTile, hand.getWallTileGroup());
                }
                currentPlayer = currentPlayer.getDownwindPlayer();
            }
        }

        hand.setStatus(HandStatus.WAITING_FOR_DISCARD);

        return boardMapper.mapToDto(handRepository.save(hand));
    }

    @Override
    public BoardDto discardTile(Long gameId, Long gamePlayerId, Long boardTileId) {

        Hand hand = getCurrentHandEntityByGameId(gameId);

        if (hand.getStatus() != HandStatus.WAITING_FOR_DISCARD) {
            throw new ProcessException(HttpStatus.BAD_REQUEST, hand.getStatus(), "Hand is not in waiting for discard state");
        }

        GamePlayer gamePlayer = null;

        if (gamePlayerId == null) {
            gamePlayer = gamePlayerService.getGamePlayerEntityByGameAndUserId(hand.getRound().getGame(), userService.getCurrentUserId());
        } else {
            gamePlayer = getGamePlayerByGameAndGamePlayerId(hand.getRound().getGame(), gamePlayerId);
        }

        if (hand.getActiveGamePlayer() != gamePlayer) {
            throw new ProcessException(HttpStatus.BAD_REQUEST, hand.getStatus(), "It is not the current player's turn");
        }

        BoardTile discardedTile = tileService.discardTile(gamePlayer.getPlayerTile(), boardTileId, operationService.createOperation(hand, gamePlayer));
        hand.setLastDiscardedTile(discardedTile);

        for (PlayerTile playerTile : hand.getPlayerTiles()) {
            GamePlayer otherGamePlayer = playerTile.getGamePlayer();
            if (otherGamePlayer == gamePlayer) {
                continue;
            }
            if (playerTile.getHandTiles().canCall(gamePlayer, discardedTile.getTile())) {
                otherGamePlayer.setStatus(GamePlayerStatus.THINKING_FOR_CALL);
                hand.setStatus(HandStatus.WAITING_FOR_CALL);
                gamePlayerService.saveGamePlayer(otherGamePlayer);
            }
        }

        if (hand.getStatus() != HandStatus.WAITING_FOR_CALL) {
            hand.setStatus(HandStatus.WAITING_FOR_DRAW);
        }

        return boardMapper.mapToDto(handRepository.save(hand));
    }

    @Override
    public PlayerViewDto cancelForCall(Long gameId, Long gamePlayerId) {

        Hand hand = getCurrentHandEntityByGameId(gameId);

        if (hand.getStatus() != HandStatus.WAITING_FOR_CALL) {
            throw new ProcessException(HttpStatus.BAD_REQUEST, hand.getStatus(), "Hand is not in waiting for call state");
        }

        GamePlayer gamePlayer = getGamePlayerByGameAndGamePlayerId(hand.getRound().getGame(), gamePlayerId);

        if (gamePlayer.getStatus() != GamePlayerStatus.THINKING_FOR_CALL) {
            throw new ProcessException(HttpStatus.BAD_REQUEST, gamePlayer.getStatus(), "GamePlayer is not in thinking for call state");
        }

        gamePlayerService.setGamePlayerStatus(gamePlayer, GamePlayerStatus.WAITING);

        if (hand.getPlayerTiles().stream().allMatch(
                playerTile -> {
                    GamePlayer otherGamePlayer = playerTile.getGamePlayer();
                    if (otherGamePlayer == gamePlayer) {
                        return true;
                    }
                    return playerTile.getGamePlayer().getStatus() == GamePlayerStatus.WAITING;
                })) {
            hand.setStatus(HandStatus.WAITING_FOR_DRAW);
            gamePlayerService.setGamePlayerStatus(hand.getActiveGamePlayer().getDownwindPlayer(), GamePlayerStatus.ABLE_TO_DRAW_TILE);
        }

        return boardMapper.mapToPlayerViewDto(handRepository.save(hand), gamePlayer);
    }

    private GamePlayer getGamePlayerByGameAndGamePlayerId(Game game, Long gamePlayerId) {
        GamePlayer gamePlayer = gamePlayerService.getGamePlayerEntityByGamePlayerId(gamePlayerId);
        if (gamePlayer.getGame() != game) {
            throw new ProcessException(HttpStatus.BAD_REQUEST, game.getCurrentHand().getStatus(), "GamePlayer does not belong to the game");
        }
        return gamePlayer;
    }
}
