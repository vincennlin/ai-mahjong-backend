package com.vincennlin.mahjongtrackerbackend.payload.game.dto.tile;

import com.fasterxml.jackson.annotation.JsonInclude;
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
public abstract class TileGroupDto {

    @JsonProperty(value = "id")
    private Long id;

    @JsonProperty(value = "tile_count")
    private int tileCount;

    @JsonProperty(value = "tiles_num")
    private String tilesNum;

    @JsonProperty(value = "tiles_sub")
    private String tilesSub;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty(value = "tiles")
    private List<BoardTileDto> tiles;
}
