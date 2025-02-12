package com.vincennlin.aimahjongbackend.mapper.game;

import com.vincennlin.aimahjongbackend.entity.game.GamePlayer;
import com.vincennlin.aimahjongbackend.entity.game.Hand;
import com.vincennlin.aimahjongbackend.payload.game.dto.GamePlayerDto;
import com.vincennlin.aimahjongbackend.payload.game.dto.HandDto;
import com.vincennlin.aimahjongbackend.payload.game.wind.Wind;
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
        handDto.setAcceptableOperations(hand.getStatus().getAcceptableOperations());
        return handDto;
    }
}
