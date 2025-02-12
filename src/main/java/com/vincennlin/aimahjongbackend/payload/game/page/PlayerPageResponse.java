package com.vincennlin.aimahjongbackend.payload.game.page;

import com.vincennlin.aimahjongbackend.payload.game.dto.PlayerDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
public class PlayerPageResponse {

    List<PlayerDto> content;

    @Schema(
            name = "pageNo",
            description = "目前頁數",
            example = "0"
    )
    private Integer pageNo;

    @Schema(
            name = "pageSize",
            description = "每頁玩家數",
            example = "10"
    )
    private Integer pageSize;

    @Schema(
            name = "totalElements",
            description = "玩家總數",
            example = "100"
    )
    private Long totalElements;

    @Schema(
            name = "totalPages",
            description = "總頁數",
            example = "10"
    )
    private Integer totalPages;

    @Schema(
            name = "last",
            description = "是否為最後一頁",
            example = "true"
    )
    private Boolean last;
}
