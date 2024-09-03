package com.vincennlin.mahjongtrackerbackend.payload.game.dto;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.vincennlin.mahjongtrackerbackend.payload.game.gamestatus.GameStatus;
import com.vincennlin.mahjongtrackerbackend.payload.user.UserDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class GameDto {

    @JsonProperty(value = "id")
    private Long id;

    @JsonProperty(value = "status")
    private GameStatus status;

    @JsonProperty(value = "creator")
    private UserDto creator;

    @JsonProperty(value = "game_players")
    private List<GamePlayerDto> gamePlayers;

    @JsonProperty(value = "base_point")
    private Integer basePoint;

    @JsonProperty(value = "fann_point")
    private Integer fannPoint;

    @JsonProperty(value = "dollar_per_point")
    private Integer dollarPerPoint;
}
