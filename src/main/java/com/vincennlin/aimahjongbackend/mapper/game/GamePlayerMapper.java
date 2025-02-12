package com.vincennlin.aimahjongbackend.mapper.game;

import com.vincennlin.aimahjongbackend.entity.game.GamePlayer;
import com.vincennlin.aimahjongbackend.payload.game.dto.GamePlayerDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GamePlayerMapper {

    private final ModelMapper modelMapper;

    public GamePlayerMapper() {
        this.modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    public GamePlayerDto mapToDto(GamePlayer gamePlayer) {
        GamePlayerDto gamePlayerDto = modelMapper.map(gamePlayer, GamePlayerDto.class);
        gamePlayerDto.setPlayerId(gamePlayer.getPlayer().getId());
        gamePlayerDto.setType(gamePlayer.getPlayer().getType());
        gamePlayerDto.setPlayerName(gamePlayer.getPlayer().getPlayerName());
        return gamePlayerDto;
    }

    public List<GamePlayerDto> mapGamePlayersToDto(List<GamePlayer> gamePlayers) {
        List<GamePlayerDto> gamePlayerDtoList = new ArrayList<>();
        gamePlayers.forEach(gamePlayer -> {
            gamePlayerDtoList.add(mapToDto(gamePlayer));
        });
        return gamePlayerDtoList;
    }
}
