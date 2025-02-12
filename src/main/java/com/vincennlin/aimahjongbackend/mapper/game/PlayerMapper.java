package com.vincennlin.aimahjongbackend.mapper.game;

import com.vincennlin.aimahjongbackend.entity.game.Player;
import com.vincennlin.aimahjongbackend.entity.user.User;
import com.vincennlin.aimahjongbackend.payload.game.dto.PlayerDto;
import com.vincennlin.aimahjongbackend.payload.user.UserDto;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

@Component
public class PlayerMapper {

    private final ModelMapper modelMapper;

    public PlayerMapper() {
        this.modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        Converter<User, UserDto> userConverter = context ->
                context.getSource() == null ? null : modelMapper.map(context.getSource(), UserDto.class);
        modelMapper.addConverter(userConverter);
    }

    public PlayerDto mapToDto(Player player) {
        PlayerDto playerDto = modelMapper.map(player, PlayerDto.class);
        playerDto.setUserName(player.getUser().getUsername());
        return playerDto;
    }
}
