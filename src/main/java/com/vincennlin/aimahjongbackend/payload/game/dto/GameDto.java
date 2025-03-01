package com.vincennlin.aimahjongbackend.payload.game.dto;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.vincennlin.aimahjongbackend.payload.game.operation.GameOperation;
import com.vincennlin.aimahjongbackend.payload.game.status.GameStatus;
import com.vincennlin.aimahjongbackend.payload.user.UserDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@JsonInclude(JsonInclude.Include.NON_NULL)
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

    @JsonProperty(value = "acceptable_operations")
    private Set<GameOperation> acceptableOperations;

    @JsonProperty(value = "creator")
    private UserDto creator;

    @JsonProperty(value = "east_player")
    private GamePlayerDto eastPlayer;

    @JsonProperty(value = "game_players")
    private List<GamePlayerDto> gamePlayers;

    @JsonProperty(value = "base_point")
    private Integer basePoint;

    @JsonProperty(value = "fann_point")
    private Integer fannPoint;

    @JsonProperty(value = "dollar_per_point")
    private Integer dollarPerPoint;
}
