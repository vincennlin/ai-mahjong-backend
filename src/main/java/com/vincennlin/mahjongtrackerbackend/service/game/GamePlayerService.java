package com.vincennlin.mahjongtrackerbackend.service.game;

import com.vincennlin.mahjongtrackerbackend.entity.game.Game;
import com.vincennlin.mahjongtrackerbackend.entity.game.GamePlayer;
import com.vincennlin.mahjongtrackerbackend.entity.game.Player;

public interface GamePlayerService {

    GamePlayer getGamePlayerEntityByPlayerId(Long playerId);

    GamePlayer createGamePlayerAndGetEntity(Game game, Player player);

    void deleteGamePlayerById(Long gamePlayerId);
}
