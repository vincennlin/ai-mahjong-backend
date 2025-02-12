package com.vincennlin.aimahjongbackend.payload.game.request.ai;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.vincennlin.aimahjongbackend.payload.game.dto.tile.BoardTileDto;
import com.vincennlin.aimahjongbackend.payload.game.dto.tile.TileGroupDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GenerateDiscardAdviceRequestTileGroup {

    public GenerateDiscardAdviceRequestTileGroup(TileGroupDto tileGroupDto) {
        this.tileCount = tileGroupDto.getTileCount();

        if (tileGroupDto.getTiles() != null) {
            StringBuilder sb = new StringBuilder();
            for (BoardTileDto tile : tileGroupDto.getTiles()) {
                sb.append(tile.getTileName()).append(" ");
            }
            this.tilesString = sb.toString();
        }
    }

    @JsonProperty(value = "tile_count")
    private int tileCount;

    @JsonProperty(value = "tiles_string")
    private String tilesString;
}
