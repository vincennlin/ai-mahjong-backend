package com.vincennlin.aimahjongbackend.controller.game.swagger;

import com.vincennlin.aimahjongbackend.payload.game.dto.GamePlayerDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Tag(
        name = "GamePlayer Controller",
        description = "遊戲內玩家相關的 API"
)
public interface GamePlayerControllerSwagger {

    @Operation(
            summary = "取得遊戲內玩家",
            description = "取得遊戲內玩家"
    )
    @ApiResponse(
            responseCode = "200",
            description = "成功取得遊戲內玩家",
            content = @Content(
                    mediaType = "application/json",
                    examples = @ExampleObject(value = """
                            {
                                "id": 8,
                                "player_id": 2,
                                "type": "BOT",
                                "player_name": "user_BOT1"
                            }
                            """)
            )
    )
    @GetMapping("/games/{game_id}/players/{player_id}/game-player")
    ResponseEntity<GamePlayerDto> getGamePlayerByGameIdAndPlayerId(@PathVariable(name = "game_id") Long gameId,
                                                                          @PathVariable(name = "player_id") Long playerId);
}
