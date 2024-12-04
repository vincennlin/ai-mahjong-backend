package com.vincennlin.mahjongtrackerbackend.controller.game.swagger;

import com.vincennlin.mahjongtrackerbackend.constant.page.PageConstants;
import com.vincennlin.mahjongtrackerbackend.payload.game.dto.PlayerDto;
import com.vincennlin.mahjongtrackerbackend.payload.game.page.PlayerPageResponse;
import com.vincennlin.mahjongtrackerbackend.payload.game.request.CreatePlayerRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(
        name = "Player Controller",
        description = "玩家相關的 API"
)
public interface PlayerControllerSwagger {

    @Operation(
            summary = "取得所有玩家",
            description = "取得所有玩家"
    )
    @ApiResponse(
            responseCode = "200",
            description = "成功取得所有玩家",
            content = @Content(
                    mediaType = "application/json",
                    examples = @ExampleObject(value = """
                            
                            """)
            )
    )
    @GetMapping("/players")
    ResponseEntity<PlayerPageResponse> getPlayers(
            @RequestParam(name = "pageNo", defaultValue = PageConstants.DEFAULT_PAGE_NUMBER, required = false) @Min(0) Integer pageNo,
            @RequestParam(name = "pageSize", defaultValue = PageConstants.DEFAULT_PAGE_SIZE, required = false) @Max(100) @Min(1) Integer pageSize,
            @RequestParam(name = "sortBy", defaultValue = PageConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(name = "sortDir", defaultValue = PageConstants.DEFAULT_SORT_DIR, required = false) String sortDir);

    @Operation(
            summary = "創建玩家",
            description = "創建玩家，可以選擇玩家類型，可以指定：HUMAN, BOT, AI"
    )
    @ApiResponse(
            responseCode = "201",
            description = "成功創建玩家",
            content = @Content(
                    mediaType = "application/json",
                    examples = @ExampleObject(value = """
                            {
                                "id": 3,
                                "type": "BOT",
                                "user_name": "user",
                                "player_name": "user_BOT2"
                            }
                            """)
            )
    )
    @PostMapping("/players")
    ResponseEntity<PlayerDto> createPlayer(@Valid @RequestBody
                                           @io.swagger.v3.oas.annotations.parameters.RequestBody(
                                                   content = @Content(
                                                           mediaType = "application/json",
                                                           examples = @ExampleObject(value = """
                                                                   {
                                                                       "type": "BOT"
                                                                   }
                                                                   """)
                                                   )
                                           ) CreatePlayerRequest request);

    @Operation(
            summary = "更新玩家",
            description = "更新玩家資訊"
    )
    @ApiResponse(
            responseCode = "200",
            description = "成功更新玩家",
            content = @Content(
                    mediaType = "application/json",
                    examples = @ExampleObject(value = """
                            {
                                "id": 1,
                                "type": "AI",
                                "user_name": "user",
                                "player_name": "user_AI2"
                            }
                            """)
            )
    )
    @PutMapping("/players/{player_id}")
    ResponseEntity<PlayerDto> updatePlayer(@PathVariable(name = "player_id") Long playerId,
                                                  @Valid @RequestBody @io.swagger.v3.oas.annotations.parameters.RequestBody(
                                                          content = @Content(
                                                                  mediaType = "application/json",
                                                                  examples = @ExampleObject(value = """
                                                                          {
                                                                              "type": "AI"
                                                                          }
                                                                          """)
                                                          )
                                                  ) PlayerDto playerDto);

    @Operation(
            summary = "刪除玩家",
            description = "刪除玩家"
    )
    @ApiResponse(
            responseCode = "204",
            description = "成功刪除玩家"
    )
    @DeleteMapping("/players/{player_id}")
    ResponseEntity<Void> deletePlayerById(@PathVariable(name = "player_id") Long playerId);
}
