package com.vincennlin.aimahjongbackend.controller.game.swagger;

import com.vincennlin.aimahjongbackend.payload.game.dto.RoundDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Tag(
        name = "Round Controller",
        description = "遊戲內回合相關的 API"
)
public interface RoundControllerSwagger {

    @Operation(
            summary = "取得遊戲內目前回合",
            description = "取得遊戲內目前回合"
    )
    @ApiResponse(
            responseCode = "200",
            description = "成功取得遊戲內目前回合",
            content = @Content(
                    mediaType = "application/json",
                    examples = @ExampleObject(value = """
                            {
                                "roundWind": "EAST"
                            }
                            """)
            )
    )
    @GetMapping("games/{game_id}/rounds/current")
    ResponseEntity<RoundDto> getCurrentRoundByGameId(@PathVariable(name = "game_id") Long gameId);
}
