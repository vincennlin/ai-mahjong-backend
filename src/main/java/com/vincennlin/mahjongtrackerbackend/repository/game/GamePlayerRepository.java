package com.vincennlin.mahjongtrackerbackend.repository.game;

import com.vincennlin.mahjongtrackerbackend.entity.game.GamePlayer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GamePlayerRepository extends JpaRepository<GamePlayer, Long> {
}
