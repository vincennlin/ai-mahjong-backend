package com.vincennlin.mahjongtrackerbackend.payload.game.dto.tile;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.vincennlin.mahjongtrackerbackend.payload.board.MeldType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExposedTileGroupDto extends PlayerTileGroupDto {

    @JsonProperty(value = "meld_type")
    private MeldType meldType;

    @JsonProperty(value = "top_board_tile")
    private BoardTileDto topBoardTile;
}
