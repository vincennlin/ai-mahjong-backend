package com.vincennlin.aimahjongbackend.payload.game.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.vincennlin.aimahjongbackend.payload.game.dto.tile.BoardTileDto;
import com.vincennlin.aimahjongbackend.payload.game.dto.tile.PlayerTileDto;
import com.vincennlin.aimahjongbackend.payload.game.dto.tile.WallTileGroupDto;
import com.vincennlin.aimahjongbackend.payload.game.operation.HandOperation;
import com.vincennlin.aimahjongbackend.payload.game.status.HandStatus;
import com.vincennlin.aimahjongbackend.payload.game.wind.Wind;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
public class BoardDto {

    public BoardDto() {
        this.playerTiles = new ArrayList<>();
    }

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

    @JsonProperty(value = "status")
    private HandStatus status;

    @JsonProperty(value = "acceptable_operations")
    private Set<HandOperation> acceptableOperations;

    @JsonProperty(value = "player_tiles")
    private List<PlayerTileDto> playerTiles;

    @JsonProperty(value = "wall_tiles")
    private WallTileGroupDto wallTiles;
}
