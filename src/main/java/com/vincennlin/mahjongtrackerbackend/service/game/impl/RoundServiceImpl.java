package com.vincennlin.mahjongtrackerbackend.service.game.impl;

import com.vincennlin.mahjongtrackerbackend.entity.game.Game;
import com.vincennlin.mahjongtrackerbackend.entity.game.Round;
import com.vincennlin.mahjongtrackerbackend.mapper.game.RoundMapper;
import com.vincennlin.mahjongtrackerbackend.payload.game.dto.RoundDto;
import com.vincennlin.mahjongtrackerbackend.repository.game.RoundRepository;
import com.vincennlin.mahjongtrackerbackend.service.game.RoundService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class RoundServiceImpl implements RoundService {

    private final RoundMapper roundMapper;

    private final RoundRepository roundRepository;

    @Override
    public Round startNewRoundAndGetEntity(Game game) {

        Round round = new Round(game, game.getNextRoundWind());

        return roundRepository.save(round);
    }

    @Override
    public RoundDto saveRound(Round round) {

        Round savedRound = roundRepository.save(round);

        return roundMapper.mapToDto(savedRound);
    }
}
