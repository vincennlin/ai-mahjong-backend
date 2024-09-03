package com.vincennlin.mahjongtrackerbackend.mapper.game;

import com.vincennlin.mahjongtrackerbackend.entity.game.GamePlayer;
import com.vincennlin.mahjongtrackerbackend.entity.game.Hand;
import com.vincennlin.mahjongtrackerbackend.payload.game.dto.GamePlayerDto;
import com.vincennlin.mahjongtrackerbackend.payload.game.dto.HandDto;
import com.vincennlin.mahjongtrackerbackend.payload.game.wind.Wind;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

@Component
public class HandMapper {

    private final ModelMapper modelMapper;

    public HandMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        Converter<GamePlayer, GamePlayerDto> gamePlayerConverter = context ->
                context.getSource() == null ? null : modelMapper.map(context.getSource(), GamePlayerDto.class);
        modelMapper.addConverter(gamePlayerConverter);
    }

    public HandDto mapToDto(Hand hand, Wind roundWind) {
        HandDto handDto = modelMapper.map(hand, HandDto.class);
        handDto.setRoundWind(roundWind);
        return handDto;
    }
}
