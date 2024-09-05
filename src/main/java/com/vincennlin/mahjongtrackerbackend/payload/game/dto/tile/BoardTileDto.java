package com.vincennlin.mahjongtrackerbackend.payload.game.dto.tile;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
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

    public BoardTileDto(String tileName) {
        this.tileName = tileName;
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty(value = "tile")
    private Tile tile;

    @JsonProperty(value = "tile_name")
    private String tileName;
}
