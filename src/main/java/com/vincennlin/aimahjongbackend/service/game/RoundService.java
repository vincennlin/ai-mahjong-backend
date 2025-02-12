package com.vincennlin.aimahjongbackend.service.game;

import com.vincennlin.aimahjongbackend.entity.game.Game;
import com.vincennlin.aimahjongbackend.entity.game.Round;
import com.vincennlin.aimahjongbackend.payload.game.dto.RoundDto;

public interface RoundService {

    RoundDto getCurrentRoundByGameId(Long gameId);

    Round startNewRoundAndGetEntity(Game game);

    Round saveRound(Round round);
}
