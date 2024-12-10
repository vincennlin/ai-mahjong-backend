package com.vincennlin.mahjongtrackerbackend.payload.game.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.vincennlin.mahjongtrackerbackend.payload.game.dto.tile.BoardTileDto;
import com.vincennlin.mahjongtrackerbackend.payload.game.dto.tile.PlayerTileDto;
import com.vincennlin.mahjongtrackerbackend.payload.game.dto.tile.WallTileGroupDto;
import com.vincennlin.mahjongtrackerbackend.payload.game.operation.GamePlayerOperation;
import com.vincennlin.mahjongtrackerbackend.payload.game.operation.HandOperation;
import com.vincennlin.mahjongtrackerbackend.payload.game.status.GamePlayerStatus;
import com.vincennlin.mahjongtrackerbackend.payload.game.status.HandStatus;
import com.vincennlin.mahjongtrackerbackend.payload.game.wind.Wind;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PlayerViewDto {

    @JsonProperty(value = "game_id")
    private Long gameId;

    @JsonProperty(value = "hand_id")
    private Long handId;

    @JsonProperty(value = "round_wind")
    private Wind RoundWind;

    @JsonProperty(value = "prevailing_wind")
    private Wind prevailingWind;

    @JsonProperty(value = "active_game_player_id")
    private Long activeGamePlayerId;

    @JsonProperty(value = "last_discarded_tile")
    private BoardTileDto lastDiscardedTile;

    @JsonProperty(value = "hand_status")
    private HandStatus handStatus;

//    @JsonProperty(value = "acceptable_operations")
//    private Set<HandOperation> acceptableOperations;

    @JsonProperty(value = "game_player_status")
    private GamePlayerStatus gamePlayerStatus;

    @JsonProperty(value = "acceptable_operations")
    private Set<GamePlayerOperation> acceptableOperations;

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

    public void hidTiles() {
        downwindPlayerTile.getHandTiles().hideTiles();
        oppositePlayerTile.getHandTiles().hideTiles();
        upwindPlayerTile.getHandTiles().hideTiles();
        wallTiles.hideTiles();
    }
}
