package com.vincennlin.mahjongtrackerbackend.service.game.impl;

import com.vincennlin.mahjongtrackerbackend.entity.game.Game;
import com.vincennlin.mahjongtrackerbackend.entity.game.GamePlayer;
import com.vincennlin.mahjongtrackerbackend.entity.game.Player;
import com.vincennlin.mahjongtrackerbackend.exception.ResourceNotFoundException;
import com.vincennlin.mahjongtrackerbackend.mapper.game.GamePlayerMapper;
import com.vincennlin.mahjongtrackerbackend.payload.game.dto.GamePlayerDto;
import com.vincennlin.mahjongtrackerbackend.repository.game.GamePlayerRepository;
import com.vincennlin.mahjongtrackerbackend.service.game.GamePlayerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public GamePlayer getGamePlayerEntityByPlayerId(Long playerId) {

        return gamePlayerRepository.getGamePlayerByPlayerId(playerId).orElseThrow(
                () -> new ResourceNotFoundException("GamePlayer", "player id", playerId));
    }

    @Transactional
    @Override
    public GamePlayer createGamePlayerAndGetEntity(Game game, Player player) {

        GamePlayer gamePlayer = new GamePlayer();
        gamePlayer.setGame(game);
        gamePlayer.setPlayer(player);

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
