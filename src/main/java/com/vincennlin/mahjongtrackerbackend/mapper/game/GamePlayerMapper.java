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
        GamePlayerDto gamePlayerDto = modelMapper.map(gamePlayer, GamePlayerDto.class);
        gamePlayerDto.setPlayerId(gamePlayer.getPlayer().getId());
        gamePlayerDto.setType(gamePlayer.getPlayer().getType());
        gamePlayerDto.setPlayerName(gamePlayer.getPlayer().getPlayerName());
        return modelMapper.map(gamePlayer, GamePlayerDto.class);
    }
}
