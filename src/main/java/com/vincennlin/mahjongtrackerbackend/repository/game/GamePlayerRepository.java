package com.vincennlin.mahjongtrackerbackend.repository.game;

import com.vincennlin.mahjongtrackerbackend.entity.game.GamePlayer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GamePlayerRepository extends JpaRepository<GamePlayer, Long> {

    Optional<GamePlayer> getGamePlayerByGameIdAndPlayerId(Long gameId, Long playerId);

    Optional<GamePlayer> getGamePlayerByPlayerId(Long playerId);
}
