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

    public BoardTileDto(Long id, int tileId, String tileName) {
        this.id = id;
        this.tileId = tileId;
        this.tileName = tileName;
    }

    @JsonProperty(value = "id")
    private Long id;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty(value = "tile")
    private Tile tile;

    @JsonProperty(value = "tile_id")
    private int tileId;

    @JsonProperty(value = "tile_name")
    private String tileName;
}
