package com.vincennlin.mahjongtrackerbackend.payload.game;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.vincennlin.mahjongtrackerbackend.constant.gamestatus.GameStatus;
import com.vincennlin.mahjongtrackerbackend.payload.user.UserDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GameDto {

    @JsonProperty(value = "id")
    private Long id;

    @JsonProperty(value = "status")
    private GameStatus status;

    @JsonProperty(value = "creator")
    private UserDto creator;

    @JsonProperty(value = "base_point")
    private int basePoint;

    @JsonProperty(value = "fann_point")
    private int fannPoint;

    @JsonProperty(value = "dollar_per_point")
    private int dollarPerPoint;
}
