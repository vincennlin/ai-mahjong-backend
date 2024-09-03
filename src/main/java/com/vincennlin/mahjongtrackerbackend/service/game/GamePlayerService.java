package com.vincennlin.mahjongtrackerbackend.service.game;

import com.vincennlin.mahjongtrackerbackend.entity.game.Game;
import com.vincennlin.mahjongtrackerbackend.entity.game.GamePlayer;
import com.vincennlin.mahjongtrackerbackend.entity.game.Player;
import com.vincennlin.mahjongtrackerbackend.payload.game.dto.GamePlayerDto;

public interface GamePlayerService {

    GamePlayerDto getGamePlayerByGameIdAndPlayerId(Long gameId, Long playerId);

    GamePlayer getGamePlayerEntityByPlayerId(Long playerId);

    GamePlayer createGamePlayerAndGetEntity(Game game, Player player);

    void deleteGamePlayerById(Long gamePlayerId);
}
