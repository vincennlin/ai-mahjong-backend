package com.vincennlin.mahjongtrackerbackend.payload.game.request.ai;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.vincennlin.mahjongtrackerbackend.payload.game.dto.tile.PlayerTileDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GenerateDiscardAdviceRequestPlayerTile {

    GenerateDiscardAdviceRequestPlayerTile(PlayerTileDto playerTileDto) {
        this.handTiles = new GenerateDiscardAdviceRequestTileGroup(playerTileDto.getHandTiles());
        this.exposedTiles = new GenerateDiscardAdviceRequestTileGroup(playerTileDto.getExposedTiles());
        this.discardedTiles = new GenerateDiscardAdviceRequestTileGroup(playerTileDto.getDiscardedTiles());
    }

    @JsonProperty(value = "hand_tiles")
    private GenerateDiscardAdviceRequestTileGroup handTiles;

    @JsonProperty(value = "exposed_tiles")
    private GenerateDiscardAdviceRequestTileGroup exposedTiles;

    @JsonProperty(value = "discarded_tiles")
    private GenerateDiscardAdviceRequestTileGroup discardedTiles;
}
