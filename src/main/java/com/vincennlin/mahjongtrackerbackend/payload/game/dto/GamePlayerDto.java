package com.vincennlin.mahjongtrackerbackend.payload.game.dto;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class GamePlayerDto {

    @JsonProperty(value = "id")
    private Long id;

    @JsonProperty(value = "player")
    private PlayerDto player;

    @JsonProperty(value = "downwind_player")
    private GamePlayerDto downwindPlayer;

//    @JsonProperty(value = "game_id")
//    private Long gameId;
//
//    @JsonProperty(value = "player_id")
//    private Long playerId;
//
//    @JsonProperty(value = "player_name")
//    private String playerName;
}
