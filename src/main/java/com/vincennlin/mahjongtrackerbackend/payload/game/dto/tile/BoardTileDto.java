package com.vincennlin.mahjongtrackerbackend.payload.game.dto.tile;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.vincennlin.mahjongtrackerbackend.payload.tile.impl.Tile;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BoardTileDto {

    @JsonProperty(value = "id")
    private Tile tile;

    @JsonProperty(value = "tile_name")
    private String tileName;
}
