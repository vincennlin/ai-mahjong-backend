package com.vincennlin.aimahjongbackend.payload.game.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.vincennlin.aimahjongbackend.payload.game.dto.tile.*;
import com.vincennlin.aimahjongbackend.payload.game.operation.GamePlayerOperation;
import com.vincennlin.aimahjongbackend.payload.game.status.GamePlayerStatus;
import com.vincennlin.aimahjongbackend.payload.game.status.HandStatus;
import com.vincennlin.aimahjongbackend.payload.game.wind.Wind;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
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

    @JsonProperty(value = "self_game_player_id")
    private Long selfGamePlayerId;

    @JsonProperty(value = "downwind_game_player_id")
    private Long downwindGamePlayerId;

    @JsonProperty(value = "opposite_game_player_id")
    private Long oppositeGamePlayerId;

    @JsonProperty(value = "upwind_game_player_id")
    private Long upwindGamePlayerId;

    @JsonProperty(value = "hand_status")
    private HandStatus handStatus;

    @JsonProperty(value = "game_player_status")
    private GamePlayerStatus gamePlayerStatus;

    @JsonProperty(value = "acceptable_operations")
    private Set<GamePlayerOperation> acceptableOperations;

    @JsonProperty(value = "last_discarded_tile")
    private BoardTileDto lastDiscardedTile;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty(value = "chow_combinations")
    private List<List<TileDto>> chowCombinations;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty(value = "concealed_kong_combinations")
    private List<TileDto> concealedKongCombinations;

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

    public void hideTiles() {
        downwindPlayerTile.getHandTiles().hideTiles();
        downwindPlayerTile.getExposedTileList().forEach(ExposedTileGroupDto::hideTiles);
        oppositePlayerTile.getHandTiles().hideTiles();
        oppositePlayerTile.getExposedTileList().forEach(ExposedTileGroupDto::hideTiles);
        upwindPlayerTile.getHandTiles().hideTiles();
        upwindPlayerTile.getExposedTileList().forEach(ExposedTileGroupDto::hideTiles);
        wallTiles.hideTiles();
    }
}
