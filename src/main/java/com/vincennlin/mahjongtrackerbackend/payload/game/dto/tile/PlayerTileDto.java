package com.vincennlin.mahjongtrackerbackend.payload.game.dto.tile;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PlayerTileDto {

    @JsonProperty(value = "id")
    private Long id;

    @JsonProperty(value = "hand_id")
    private Long handId;

    @JsonProperty(value = "game_player_id")
    private Long gamePlayerId;

    @JsonProperty(value = "hand_tiles")
    private PlayerTileGroupDto handTiles;

    @JsonProperty(value = "flower_tiles")
    private PlayerTileGroupDto flowerTiles;

    @JsonProperty(value = "exposed_tile_list")
    private List<ExposedTileGroupDto> exposedTileList;

    @JsonProperty(value = "discarded_tiles")
    private PlayerTileGroupDto discardedTiles;
}
