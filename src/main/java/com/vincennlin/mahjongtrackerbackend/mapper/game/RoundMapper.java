package com.vincennlin.mahjongtrackerbackend.mapper.game;

import com.vincennlin.mahjongtrackerbackend.entity.game.Round;
import com.vincennlin.mahjongtrackerbackend.payload.game.dto.RoundDto;
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
