package com.vincennlin.mahjongtrackerbackend.service.game;

import com.vincennlin.mahjongtrackerbackend.entity.game.Game;
import com.vincennlin.mahjongtrackerbackend.entity.game.GamePlayer;
import com.vincennlin.mahjongtrackerbackend.entity.game.Player;
import com.vincennlin.mahjongtrackerbackend.payload.game.dto.GamePlayerDto;
import com.vincennlin.mahjongtrackerbackend.payload.game.status.GamePlayerStatus;

public interface GamePlayerService {

    GamePlayerDto getGamePlayerByGameIdAndPlayerId(Long gameId, Long playerId);

    GamePlayer getGamePlayerEntityByGamePlayerId(Long gamePlayerId);

    GamePlayer getGamePlayerEntityByPlayerId(Long playerId);

    GamePlayer getGamePlayerEntityByGameAndUserId(Game game, Long currentUserId);

    GamePlayer createGamePlayerAndGetEntity(Game game, Player player);

    GamePlayer saveGamePlayer(GamePlayer gamePlayer);

    GamePlayer setGamePlayerStatus(GamePlayer gamePlayer, GamePlayerStatus status);

    void deleteGamePlayerById(Long gamePlayerId);
}
