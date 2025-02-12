package com.vincennlin.aimahjongbackend.mapper.game;

import com.vincennlin.aimahjongbackend.entity.game.Game;
import com.vincennlin.aimahjongbackend.entity.user.User;
import com.vincennlin.aimahjongbackend.payload.game.dto.GameDto;
import com.vincennlin.aimahjongbackend.payload.user.UserDto;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

@Component
public class GameMapper {

    private final ModelMapper modelMapper;
    private final GamePlayerMapper gamePlayerMapper;

    public GameMapper() {
        this.modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        this.gamePlayerMapper = new GamePlayerMapper();

        Converter<User, UserDto> userConverter = context ->
                context.getSource() == null ? null : modelMapper.map(context.getSource(), UserDto.class);
        modelMapper.addConverter(userConverter);

//        Converter<GamePlayer, GamePlayerDto> gamePlayerConverter = context ->{
//            GamePlayer source = context.getSource();
//            if (source == null) {
//                return null;
//            }
//            GamePlayerDto destination = new GamePlayerDto();
//            destination.setId(source.getId());
//            destination.setPlayerId(source.getPlayer().getId());
//            destination.setGameId(source.getGame().getId());
//            destination.setPlayerName(source.getPlayer().getPlayerName());
//            return destination;
//        };
//        modelMapper.addConverter(gamePlayerConverter);
    }

    public GameDto mapToDto(Game game) {
        GameDto gameDto = modelMapper.map(game, GameDto.class);
        gameDto.setAcceptableOperations(game.getStatus().getAcceptableOperations());
        gameDto.setGamePlayers(gamePlayerMapper.mapGamePlayersToDto(game.getGamePlayers()));
        if (game.getEastPlayer() != null) {
            gameDto.setEastPlayer(gamePlayerMapper.mapToDto(game.getEastPlayer()));
        }
        return gameDto;
    }
}
