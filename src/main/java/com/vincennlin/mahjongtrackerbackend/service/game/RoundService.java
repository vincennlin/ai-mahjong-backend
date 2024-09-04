package com.vincennlin.mahjongtrackerbackend.service.game;

import com.vincennlin.mahjongtrackerbackend.entity.game.Game;
import com.vincennlin.mahjongtrackerbackend.entity.game.Round;
import com.vincennlin.mahjongtrackerbackend.payload.game.dto.RoundDto;

public interface RoundService {

    RoundDto getCurrentRoundByGameId(Long gameId);

    Round startNewRoundAndGetEntity(Game game);

    RoundDto saveRound(Round round);
}
