package com.vincennlin.mahjongtrackerbackend.payload.game;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.vincennlin.mahjongtrackerbackend.constant.playertype.PlayerType;
import com.vincennlin.mahjongtrackerbackend.payload.user.UserDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PlayerDto {

    @JsonProperty(value = "id")
    private Long id;

    @JsonProperty(value = "type")
    private PlayerType type;

    @JsonProperty(value = "user")
    private UserDto user;
}
