package com.vincennlin.mahjongtrackerbackend.mapper.game;

import com.vincennlin.mahjongtrackerbackend.entity.game.GamePlayer;
import com.vincennlin.mahjongtrackerbackend.payload.game.dto.GamePlayerDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

@Component
public class GamePlayerMapper {

    private final ModelMapper modelMapper;

    public GamePlayerMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    public GamePlayerDto mapToDto(GamePlayer gamePlayer) {
        return modelMapper.map(gamePlayer, GamePlayerDto.class);
    }
}
