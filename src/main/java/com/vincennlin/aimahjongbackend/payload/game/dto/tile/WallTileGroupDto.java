package com.vincennlin.aimahjongbackend.payload.game.dto.tile;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WallTileGroupDto extends TileGroupDto {

    @JsonProperty(value = "hand_id")
    private Long handId;
}
