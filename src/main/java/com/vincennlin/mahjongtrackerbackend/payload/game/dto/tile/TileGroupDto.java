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

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty(value = "last_drawn_tile_num")
    private Character lastDrawnTileNum;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty(value = "last_drawn_tile_sub")
    private Character lastDrawnTileSub;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty(value = "tiles_num")
    private String tilesNum;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty(value = "tiles_sub")
    private String tilesSub;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty(value = "tiles")
    private List<BoardTileDto> tiles;

    public void hideTiles() {
        setTiles(null);
        setTilesNum("*".repeat(tileCount));
        setTilesSub("*".repeat(tileCount));
        if (lastDrawnTileNum != null) {
            setLastDrawnTileNum('*');
            setLastDrawnTileSub('*');
        }
    }
}
