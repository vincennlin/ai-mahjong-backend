package com.vincennlin.aimahjongbackend.service.game.impl;

import com.vincennlin.aimahjongbackend.entity.game.Game;
import com.vincennlin.aimahjongbackend.entity.game.Round;
import com.vincennlin.aimahjongbackend.mapper.game.RoundMapper;
import com.vincennlin.aimahjongbackend.payload.game.dto.RoundDto;
import com.vincennlin.aimahjongbackend.repository.game.RoundRepository;
import com.vincennlin.aimahjongbackend.service.game.GameService;
import com.vincennlin.aimahjongbackend.service.game.RoundService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class RoundServiceImpl implements RoundService {

    private final RoundMapper roundMapper;

    private final GameService gameService;

    private final RoundRepository roundRepository;

    @Override
    public RoundDto getCurrentRoundByGameId(Long gameId) {

        Game game = gameService.getGameEntityById(gameId);

        Round round = game.getCurrentRound();

        return roundMapper.mapToDto(round);
    }

    @Override
    public Round startNewRoundAndGetEntity(Game game) {

        Round round = new Round(game, game.getNextRoundWind());

        return roundRepository.save(round);
    }

    @Override
    public Round saveRound(Round round) {

        return roundRepository.save(round);
    }
}
