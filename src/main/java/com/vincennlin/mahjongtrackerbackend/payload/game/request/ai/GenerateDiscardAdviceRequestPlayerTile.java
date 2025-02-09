package com.vincennlin.mahjongtrackerbackend.payload.game.request.ai;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.vincennlin.mahjongtrackerbackend.payload.game.dto.tile.PlayerTileDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GenerateDiscardAdviceRequestPlayerTile {

    GenerateDiscardAdviceRequestPlayerTile(PlayerTileDto playerTileDto) {
        this.handTiles = new GenerateDiscardAdviceRequestTileGroup(playerTileDto.getHandTiles());
        this.exposedTiles = playerTileDto.getExposedTileList().stream()
                .map(GenerateDiscardAdviceRequestTileGroup::new)
                .toList();
        this.flowerTiles = new GenerateDiscardAdviceRequestTileGroup(playerTileDto.getFlowerTiles());
        this.discardedTiles = new GenerateDiscardAdviceRequestTileGroup(playerTileDto.getDiscardedTiles());
    }

    @JsonProperty(value = "hand_tiles")
    private GenerateDiscardAdviceRequestTileGroup handTiles;

    @JsonProperty(value = "exposed_tiles")
    private List<GenerateDiscardAdviceRequestTileGroup> exposedTiles;

    @JsonProperty(value = "flower_tiles")
    private GenerateDiscardAdviceRequestTileGroup flowerTiles;

    @JsonProperty(value = "discarded_tiles")
    private GenerateDiscardAdviceRequestTileGroup discardedTiles;
}
