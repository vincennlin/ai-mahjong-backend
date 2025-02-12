package com.vincennlin.aimahjongbackend.mapper.game;

import com.vincennlin.aimahjongbackend.entity.game.Round;
import com.vincennlin.aimahjongbackend.payload.game.dto.RoundDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

@Component
public class RoundMapper {

    private final ModelMapper modelMapper;

    public RoundMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    public RoundDto mapToDto(Round round) {
        return modelMapper.map(round, RoundDto.class);
    }
}
