package com.vincennlin.mahjongtrackerbackend.payload.game.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.vincennlin.mahjongtrackerbackend.payload.game.dto.tile.PlayerTileDto;
import com.vincennlin.mahjongtrackerbackend.payload.game.dto.tile.WallTileGroupDto;
import com.vincennlin.mahjongtrackerbackend.payload.game.wind.Wind;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class BoardDto {

    public BoardDto() {
        this.playerTiles = new ArrayList<>();
    }

    @JsonProperty(value = "hand_id")
    private Long handId;

    @JsonProperty(value = "wall_tiles")
    private WallTileGroupDto wallTiles;

    @JsonProperty(value = "player_tiles")
    private List<PlayerTileDto> playerTiles;

    @JsonProperty(value = "round_wind")
    private Wind RoundWind;

    @JsonProperty(value = "prevailing_wind")
    private Wind prevailingWind;
}
