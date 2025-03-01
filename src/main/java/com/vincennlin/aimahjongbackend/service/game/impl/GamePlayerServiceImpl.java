package com.vincennlin.aimahjongbackend.service.game.impl;

import com.vincennlin.aimahjongbackend.entity.game.Game;
import com.vincennlin.aimahjongbackend.entity.game.GamePlayer;
import com.vincennlin.aimahjongbackend.entity.game.Player;
import com.vincennlin.aimahjongbackend.exception.ResourceNotFoundException;
import com.vincennlin.aimahjongbackend.mapper.game.GamePlayerMapper;
import com.vincennlin.aimahjongbackend.payload.game.dto.GamePlayerDto;
import com.vincennlin.aimahjongbackend.payload.game.playertype.PlayerType;
import com.vincennlin.aimahjongbackend.payload.game.status.GamePlayerStatus;
import com.vincennlin.aimahjongbackend.repository.game.GamePlayerRepository;
import com.vincennlin.aimahjongbackend.service.game.GamePlayerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@AllArgsConstructor
@Service
public class GamePlayerServiceImpl implements GamePlayerService {

    private final GamePlayerRepository gamePlayerRepository;

    private final GamePlayerMapper gamePlayerMapper;

    @Override
    public GamePlayerDto getGamePlayerByGameIdAndPlayerId(Long gameId, Long playerId) {

        GamePlayer gamePlayer = gamePlayerRepository.getGamePlayerByGameIdAndPlayerId(gameId, playerId).orElseThrow(
                () -> new ResourceNotFoundException("GamePlayer", "game id and player id", gameId));

        return gamePlayerMapper.mapToDto(gamePlayer);
    }

    @Override
    public GamePlayer getGamePlayerEntityByGamePlayerId(Long gamePlayerId) {
        return gamePlayerRepository.findById(gamePlayerId).orElseThrow(
                () -> new ResourceNotFoundException("GamePlayer", "id", gamePlayerId));
    }

    @Override
    public GamePlayer getGamePlayerEntityByPlayerId(Long playerId) {

        return gamePlayerRepository.getGamePlayerByPlayerId(playerId).orElseThrow(
                () -> new ResourceNotFoundException("GamePlayer", "player id", playerId));
    }

    @Override
    public GamePlayer getGamePlayerEntityByGameAndUserId(Game game, Long currentUserId) {

        List<GamePlayer> gamePlayers = game.getGamePlayers();

        return gamePlayers.stream().filter(
                gamePlayer ->
                        gamePlayer.getPlayer().getType() == PlayerType.HUMAN && gamePlayer.getPlayer().getUser().getId().equals(currentUserId)
        ).findFirst().orElseThrow(
                () -> new ResourceNotFoundException("GamePlayer", "game id and player id", game.getId()));
    }

    @Transactional
    @Override
    public GamePlayer createGamePlayerAndGetEntity(Game game, Player player) {

        GamePlayer gamePlayer = new GamePlayer(game, player);

        return gamePlayerRepository.save(gamePlayer);
    }

    @Override
    public GamePlayer saveGamePlayer(GamePlayer gamePlayer) {

        return gamePlayerRepository.save(gamePlayer);
    }

    @Override
    public GamePlayer setGamePlayerStatus(GamePlayer gamePlayer, GamePlayerStatus status) {

        gamePlayer.setStatus(status);

        return gamePlayerRepository.save(gamePlayer);
    }

    @Transactional
    @Override
    public void deleteGamePlayerById(Long gamePlayerId) {

        GamePlayer gamePlayer = gamePlayerRepository.findById(gamePlayerId).orElseThrow(
                () -> new ResourceNotFoundException("GamePlayer", "id", gamePlayerId));

        gamePlayerRepository.delete(gamePlayer);
    }
}
