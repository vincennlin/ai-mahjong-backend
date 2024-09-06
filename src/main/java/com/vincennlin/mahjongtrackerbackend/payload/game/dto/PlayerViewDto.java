package com.vincennlin.mahjongtrackerbackend.payload.game.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.vincennlin.mahjongtrackerbackend.payload.game.dto.tile.PlayerTileDto;
import com.vincennlin.mahjongtrackerbackend.payload.game.dto.tile.WallTileGroupDto;
import com.vincennlin.mahjongtrackerbackend.payload.game.wind.Wind;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PlayerViewDto {

    @JsonProperty(value = "hand_id")
    private Long handId;

    @JsonProperty(value = "round_wind")
    private Wind RoundWind;

    @JsonProperty(value = "prevailing_wind")
    private Wind prevailingWind;

    @JsonProperty(value = "player_tile")
    private PlayerTileDto playerTile;

    @JsonProperty(value = "downwind_player_tile")
    private PlayerTileDto downwindPlayerTile;

    @JsonProperty(value = "opposite_player_tile")
    private PlayerTileDto oppositePlayerTile;

    @JsonProperty(value = "upwind_player_tile")
    private PlayerTileDto upwindPlayerTile;

    @JsonProperty(value = "wall_tiles")
    private WallTileGroupDto wallTiles;
}
