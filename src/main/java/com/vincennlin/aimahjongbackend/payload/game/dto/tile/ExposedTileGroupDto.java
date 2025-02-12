package com.vincennlin.aimahjongbackend.payload.game.dto.tile;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.vincennlin.aimahjongbackend.payload.board.MeldType;
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
