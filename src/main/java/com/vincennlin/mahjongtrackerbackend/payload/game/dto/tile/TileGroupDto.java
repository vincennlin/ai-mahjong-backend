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
public abstract class TileGroupDto {

    @JsonProperty(value = "id")
    private Long id;

    @JsonProperty(value = "tiles")
    private List<BoardTileDto> tiles;
}
