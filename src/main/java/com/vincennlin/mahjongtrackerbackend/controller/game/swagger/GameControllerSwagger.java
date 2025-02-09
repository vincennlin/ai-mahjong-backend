package com.vincennlin.mahjongtrackerbackend.controller.game.swagger;

import com.vincennlin.mahjongtrackerbackend.constant.page.PageConstants;
import com.vincennlin.mahjongtrackerbackend.payload.game.dto.GameDto;
import com.vincennlin.mahjongtrackerbackend.payload.game.dto.PlayerViewDto;
import com.vincennlin.mahjongtrackerbackend.payload.game.page.GamePageResponse;
import com.vincennlin.mahjongtrackerbackend.payload.game.request.game.CreateGameRequest;
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
        name = "Game Controller",
        description = "遊戲相關的 API"
)
public interface GameControllerSwagger {

    @Operation(
            summary = "取得所有遊戲",
            description = "取得所有遊戲，並可加入分頁等資訊"
    )
    @ApiResponse(
            responseCode = "200",
            description = "成功取得所有遊戲",
            content = @Content(
                    mediaType = "application/json",
                    examples = @ExampleObject(value = """
                            {
                                "content": [
                                    {
                                        "id": 1,
                                        "status": "WAITING_FOR_PLAYERS",
                                        "acceptable_operations": [
                                            "ADD_PLAYER"
                                        ],
                                        "creator": {
                                            "id": 2,
                                            "name": "user",
                                            "username": "user"
                                        },
                                        "game_players": [
                                            {
                                                "id": 1,
                                                "player_id": 1,
                                                "type": "AI",
                                                "player_name": "user_AI2"
                                            }
                                        ],
                                        "base_point": 5,
                                        "fann_point": 2,
                                        "dollar_per_point": 10
                                    },
                                    {
                                        "id": 2,
                                        "status": "WAITING_FOR_PLAYERS",
                                        "acceptable_operations": [
                                            "ADD_PLAYER"
                                        ],
                                        "creator": {
                                            "id": 2,
                                            "name": "user",
                                            "username": "user"
                                        },
                                        "game_players": [
                                            {
                                                "id": 2,
                                                "player_id": 4,
                                                "type": "HUMAN",
                                                "player_name": "user"
                                            }
                                        ],
                                        "base_point": 3,
                                        "fann_point": 1,
                                        "dollar_per_point": 10
                                    }
                                ],
                                "pageNo": 0,
                                "pageSize": 10,
                                "totalElements": 2,
                                "totalPages": 1,
                                "last": true
                            }
                            """)
            )
    )
    @GetMapping("/games")
    ResponseEntity<GamePageResponse> getGames(
            @RequestParam(name = "pageNo", defaultValue = PageConstants.DEFAULT_PAGE_NUMBER, required = false) @Min(0) Integer pageNo,
            @RequestParam(name = "pageSize", defaultValue = PageConstants.DEFAULT_PAGE_SIZE, required = false) @Max(100) @Min(1) Integer pageSize,
            @RequestParam(name = "sortBy", defaultValue = PageConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(name = "sortDir", defaultValue = PageConstants.DEFAULT_SORT_DIR, required = false) String sortDir);

    @Operation(
            summary = "取得遊戲",
            description = "根據 id 取得遊戲"
    )
    @ApiResponse(
            responseCode = "200",
            description = "成功取得遊戲",
            content = @Content(
                    mediaType = "application/json",
                    examples = @ExampleObject(value = """
                            {
                                "id": 2,
                                "status": "WAITING_FOR_PLAYERS",
                                "acceptable_operations": [
                                    "ADD_PLAYER"
                                ],
                                "creator": {
                                    "id": 2,
                                    "name": "user",
                                    "username": "user"
                                },
                                "game_players": [
                                    {
                                        "id": 2,
                                        "player_id": 4,
                                        "type": "HUMAN",
                                        "player_name": "user"
                                    }
                                ],
                                "base_point": 3,
                                "fann_point": 1,
                                "dollar_per_point": 10
                            }
                            """)
            )
    )
    @GetMapping("/games/{game_id}")
    ResponseEntity<GameDto> getGameById(@PathVariable(name = "game_id") Long gameId);

    @Operation(
            summary = "[NEW] 自動開始遊戲",
            description = "自動開始遊戲至準備捨牌的階段，並加入 user2, user3, user4 共 3 位 HUMAN 玩家"
    )
    @ApiResponse(
            responseCode = "200",
            description = "成功自動開始遊戲",
            content = @Content(
                    mediaType = "application/json",
                    examples = @ExampleObject(value = """
                            {
                                "game_id": 1,
                                "hand_id": 1,
                                "round_wind": "EAST",
                                "prevailing_wind": "EAST",
                                "active_game_player_id": 3,
                                "last_discarded_tile": null,
                                "hand_status": "WAITING_FOR_DISCARD",
                                "game_player_status": "WAITING",
                                "acceptable_operations": [],
                                "player_tile": {
                                    "id": 2,
                                    "hand_id": 1,
                                    "game_player_id": 1,
                                    "hand_tiles": {
                                        "id": 5,
                                        "tile_count": 16,
                                        "tiles_num": "三四八九 五九 五七七七八 東南西 發白",
                                        "tiles_sub": "萬萬萬萬 筒筒 條條條條條 風風風 財板",
                                        "tiles": [
                                            {
                                                "id": 54,
                                                "tile_id": 2,
                                                "tile_name": "三萬"
                                            },
                                            {
                                                "id": 24,
                                                "tile_id": 3,
                                                "tile_name": "四萬"
                                            },
                                            {
                                                "id": 7,
                                                "tile_id": 7,
                                                "tile_name": "八萬"
                                            },
                                            {
                                                "id": 21,
                                                "tile_id": 8,
                                                "tile_name": "九萬"
                                            },
                                            {
                                                "id": 56,
                                                "tile_id": 13,
                                                "tile_name": "五筒"
                                            },
                                            {
                                                "id": 23,
                                                "tile_id": 17,
                                                "tile_name": "九筒"
                                            },
                                            {
                                                "id": 40,
                                                "tile_id": 22,
                                                "tile_name": "五條"
                                            },
                                            {
                                                "id": 6,
                                                "tile_id": 24,
                                                "tile_name": "七條"
                                            },
                                            {
                                                "id": 8,
                                                "tile_id": 24,
                                                "tile_name": "七條"
                                            },
                                            {
                                                "id": 38,
                                                "tile_id": 24,
                                                "tile_name": "七條"
                                            },
                                            {
                                                "id": 37,
                                                "tile_id": 25,
                                                "tile_name": "八條"
                                            },
                                            {
                                                "id": 143,
                                                "tile_id": 27,
                                                "tile_name": "東風"
                                            },
                                            {
                                                "id": 39,
                                                "tile_id": 28,
                                                "tile_name": "南風"
                                            },
                                            {
                                                "id": 55,
                                                "tile_id": 29,
                                                "tile_name": "西風"
                                            },
                                            {
                                                "id": 53,
                                                "tile_id": 32,
                                                "tile_name": "發財"
                                            },
                                            {
                                                "id": 22,
                                                "tile_id": 33,
                                                "tile_name": "白板"
                                            }
                                        ],
                                        "player_id": 1
                                    },
                                    "flower_tiles": {
                                        "id": 6,
                                        "tile_count": 1,
                                        "tiles_num": "蘭",
                                        "tiles_sub": "＝",
                                        "tiles": [
                                            {
                                                "id": 5,
                                                "tile_id": 39,
                                                "tile_name": "蘭"
                                            }
                                        ],
                                        "player_id": 1
                                    },
                                    "exposed_tile_list": [],
                                    "discarded_tiles": {
                                        "id": 7,
                                        "tile_count": 0,
                                        "tiles_num": "",
                                        "tiles_sub": "",
                                        "tiles": [],
                                        "player_id": 1
                                    }
                                },
                                "downwind_player_tile": {
                                    "id": 3,
                                    "hand_id": 1,
                                    "game_player_id": 2,
                                    "hand_tiles": {
                                        "id": 8,
                                        "tile_count": 16,
                                        "tiles_num": "****************",
                                        "tiles_sub": "****************",
                                        "player_id": 2
                                    },
                                    "flower_tiles": {
                                        "id": 9,
                                        "tile_count": 0,
                                        "tiles_num": "",
                                        "tiles_sub": "",
                                        "tiles": [],
                                        "player_id": 2
                                    },
                                    "exposed_tile_list": [],
                                    "discarded_tiles": {
                                        "id": 10,
                                        "tile_count": 0,
                                        "tiles_num": "",
                                        "tiles_sub": "",
                                        "tiles": [],
                                        "player_id": 2
                                    }
                                },
                                "opposite_player_tile": {
                                    "id": 4,
                                    "hand_id": 1,
                                    "game_player_id": 4,
                                    "hand_tiles": {
                                        "id": 11,
                                        "tile_count": 16,
                                        "tiles_num": "****************",
                                        "tiles_sub": "****************",
                                        "player_id": 4
                                    },
                                    "flower_tiles": {
                                        "id": 12,
                                        "tile_count": 0,
                                        "tiles_num": "",
                                        "tiles_sub": "",
                                        "tiles": [],
                                        "player_id": 4
                                    },
                                    "exposed_tile_list": [],
                                    "discarded_tiles": {
                                        "id": 13,
                                        "tile_count": 0,
                                        "tiles_num": "",
                                        "tiles_sub": "",
                                        "tiles": [],
                                        "player_id": 4
                                    }
                                },
                                "upwind_player_tile": {
                                    "id": 1,
                                    "hand_id": 1,
                                    "game_player_id": 3,
                                    "hand_tiles": {
                                        "id": 2,
                                        "tile_count": 17,
                                        "tiles_num": "*****************",
                                        "tiles_sub": "*****************",
                                        "player_id": 3
                                    },
                                    "flower_tiles": {
                                        "id": 3,
                                        "tile_count": 1,
                                        "tiles_num": "梅",
                                        "tiles_sub": "＝",
                                        "tiles": [
                                            {
                                                "id": 18,
                                                "tile_id": 38,
                                                "tile_name": "梅"
                                            }
                                        ],
                                        "player_id": 3
                                    },
                                    "exposed_tile_list": [],
                                    "discarded_tiles": {
                                        "id": 4,
                                        "tile_count": 0,
                                        "tiles_num": "",
                                        "tiles_sub": "",
                                        "tiles": [],
                                        "player_id": 3
                                    }
                                },
                                "wall_tiles": {
                                    "id": 1,
                                    "tile_count": 77,
                                    "tiles_num": "*****************************************************************************",
                                    "tiles_sub": "*****************************************************************************",
                                    "hand_id": 1
                                }
                            }
                            """)
            )
    )
    @PostMapping("/games/auto-start")
    ResponseEntity<PlayerViewDto> autoStartGame(@Valid @RequestBody
                                           @io.swagger.v3.oas.annotations.parameters.RequestBody(
                                                   content = @Content(
                                                           mediaType = "application/json",
                                                           examples = @ExampleObject(value = """
                                                                   {
                                                                       "base_point": "5",
                                                                       "fann_point": "2",
                                                                       "dollar_per_point": "10"
                                                                   }
                                                                   """)
                                                   )
                                           ) CreateGameRequest request);

    @Operation(
            summary = "創建遊戲",
            description = "創建遊戲，並指定底、台、一台幾元等"
    )
    @ApiResponse(
            responseCode = "201",
            description = "成功創建遊戲",
            content = @io.swagger.v3.oas.annotations.media.Content(
                    mediaType = "application/json",
                    examples = @io.swagger.v3.oas.annotations.media.ExampleObject(value = """
                            {
                                "id": 2,
                                "status": "WAITING_FOR_PLAYERS",
                                "acceptable_operations": [
                                    "ADD_PLAYER"
                                ],
                                "creator": {
                                    "id": 2,
                                    "name": "user",
                                    "username": "user"
                                },
                                "game_players": [
                                    {
                                        "id": 2,
                                        "player_id": 4,
                                        "type": "HUMAN",
                                        "player_name": "user"
                                    }
                                ],
                                "base_point": 5,
                                "fann_point": 2,
                                "dollar_per_point": 10
                            }
                            """)
            )
    )
    @PostMapping("/games")
    ResponseEntity<GameDto> createGame(@Valid @RequestBody
                                       @io.swagger.v3.oas.annotations.parameters.RequestBody(
                                               content = @Content(
                                                       mediaType = "application/json",
                                                       examples = @ExampleObject(value = """
                                                               {
                                                                   "base_point": "5",
                                                                   "fann_point": "2",
                                                                   "dollar_per_point": "10"
                                                               }
                                                               """)
                                               )
                                       ) CreateGameRequest request);

    @Operation(
            summary = "更新遊戲",
            description = "更新遊戲資訊"
    )
    @ApiResponse(
responseCode = "200",
            description = "成功更新遊戲",
            content = @Content(
                    mediaType = "application/json",
                    examples = @ExampleObject(value = """
                            {
                                "id": 2,
                                "status": "WAITING_FOR_PLAYERS",
                                "acceptable_operations": [
                                    "ADD_PLAYER"
                                ],
                                "creator": {
                                    "id": 2,
                                    "name": "user",
                                    "username": "user"
                                },
                                "game_players": [
                                    {
                                        "id": 2,
                                        "player_id": 4,
                                        "type": "HUMAN",
                                        "player_name": "user"
                                    }
                                ],
                                "base_point": 3,
                                "fann_point": 1,
                                "dollar_per_point": 10
                            }
                            """)
            )
    )
    @PutMapping("/games/{game_id}")
    ResponseEntity<GameDto> updateGame(@PathVariable(name = "game_id") Long gameId,
                                              @Valid @RequestBody
                                              @io.swagger.v3.oas.annotations.parameters.RequestBody(
                                                      content = @Content(
                                                              mediaType = "application/json",
                                                              examples = @ExampleObject(value = """
                                                                      {
                                                                          "base_point": "3",
                                                                          "fann_point": "1",
                                                                          "dollar_per_point": "10"
                                                                      }
                                                                      """)
                                                      )
                                              ) GameDto gameDto);

    @Operation(
            summary = "刪除遊戲",
            description = "根據 id 刪除遊戲"
    )
    @DeleteMapping("/games/{game_id}")
    ResponseEntity<Void> deleteGameById(@PathVariable(name = "game_id") Long gameId);

    @Operation(
            summary = "加入玩家至遊戲等待大廳",
            description = "根據遊戲 id 與玩家 id 新增玩家至遊戲等待大廳，只有在遊戲狀態為 \"WAITING_FOR_PLAYERS\" 時才能加入。若新增後滿四人，則將遊戲狀態改為 \"READY_TO_START\"。"
    )
    @ApiResponse(
            responseCode = "200",
            description = "成功加入玩家至遊戲",
            content = @Content(
                    mediaType = "application/json",
                    examples = @ExampleObject(value = """
                            {
                                "id": 1,
                                "status": "WAITING_FOR_PLAYERS",
                                "acceptable_operations": [
                                    "ADD_PLAYER"
                                ],
                                "creator": {
                                    "id": 2,
                                    "name": "user",
                                    "username": "user"
                                },
                                "game_players": [
                                    {
                                        "id": 1,
                                        "player_id": 1,
                                        "type": "AI",
                                        "player_name": "user_AI2"
                                    },
                                    {
                                        "id": 7,
                                        "player_id": 2,
                                        "type": "BOT",
                                        "player_name": "user_BOT1"
                                    }
                                ],
                                "base_point": 5,
                                "fann_point": 2,
                                "dollar_per_point": 10
                            }
                            """)
            )
    )
    @PutMapping("/games/{game_id}/players/{player_id}")
    ResponseEntity<GameDto> addPlayerToGame(@PathVariable(name = "game_id") Long gameId,
                                                   @PathVariable(name = "player_id") Long playerId);

    @Operation(
            summary = "將玩家移除遊戲等待大廳",
            description = "根據遊戲 id 與玩家 id 將玩家從遊戲等待大廳移除，只有在遊戲狀態為 \"WAITING_FOR_PLAYERS\" 時才能移除。"
    )
    @ApiResponse(
            responseCode = "200",
            description = "成功移除玩家",
            content = @Content(
                    mediaType = "application/json",
                    examples = @ExampleObject(value = """
                            {
                                "id": 1,
                                "status": "WAITING_FOR_PLAYERS",
                                "acceptable_operations": [
                                    "ADD_PLAYER"
                                ],
                                "creator": {
                                    "id": 2,
                                    "name": "user",
                                    "username": "user"
                                },
                                "game_players": [
                                    {
                                        "id": 1,
                                        "player_id": 1,
                                        "type": "AI",
                                        "player_name": "user_AI2"
                                    }
                                ],
                                "base_point": 5,
                                "fann_point": 2,
                                "dollar_per_point": 10
                            }
                            """)
            )
    )
    @DeleteMapping("/games/{game_id}/players/{player_id}")
    ResponseEntity<GameDto> removePlayerFromGame(@PathVariable(name = "game_id") Long gameId,
                                                        @PathVariable(name = "player_id") Long playerId);

    @Operation(
            summary = "開始遊戲與抓位",
            description = "開始遊戲與抓位，只有在遊戲狀態為 \"READY_TO_START\" 時能開始。完成抓位後將遊戲狀態改為 \"FINISHED_PICKING_SEATS\"。"
    )
    @ApiResponse(
            responseCode = "200",
            description = "成功開始遊戲與抓位",
            content = @Content(
                    mediaType = "application/json",
                    examples = @ExampleObject(value = """
                            {
                                "id": 1,
                                "status": "FINISHED_PICKING_SEATS",
                                "acceptable_operations": [
                                    "DECIDE_EAST_PLAYER"
                                ],
                                "creator": {
                                    "id": 2,
                                    "name": "user",
                                    "username": "user"
                                },
                                "east_player": {
                                    "id": 9,
                                    "player_id": 3,
                                    "type": "BOT",
                                    "player_name": "user_BOT2"
                                },
                                "game_players": [
                                    {
                                        "id": 9,
                                        "player_id": 3,
                                        "type": "BOT",
                                        "player_name": "user_BOT2"
                                    },
                                    {
                                        "id": 1,
                                        "player_id": 1,
                                        "type": "AI",
                                        "player_name": "user_AI2"
                                    },
                                    {
                                        "id": 8,
                                        "player_id": 2,
                                        "type": "BOT",
                                        "player_name": "user_BOT1"
                                    },
                                    {
                                        "id": 10,
                                        "player_id": 4,
                                        "type": "HUMAN",
                                        "player_name": "user"
                                    }
                                ],
                                "base_point": 5,
                                "fann_point": 2,
                                "dollar_per_point": 10
                            }
                            """)
            )
    )
    @PutMapping("/games/{game_id}/pick-seats")
    ResponseEntity<GameDto> pickSeats(@PathVariable(name = "game_id") Long gameId);

    @Operation(
            summary = "決定東家",
            description = "決定東家，只有在遊戲狀態為 \"FINISHED_PICKING_SEATS\" 時能決定。決定東家後將遊戲狀態改為 \"READY_TO_START_NEW_ROUND\"。"
    )
    @ApiResponse(
            responseCode = "200",
            description = "成功決定東家",
            content = @Content(
                    mediaType = "application/json",
                    examples = @ExampleObject(value = """
                            {
                                "id": 1,
                                "status": "READY_TO_START_NEW_ROUND",
                                "acceptable_operations": [
                                    "START_NEW_HAND"
                                ],
                                "creator": {
                                    "id": 2,
                                    "name": "user",
                                    "username": "user"
                                },
                                "east_player": {
                                    "id": 10,
                                    "player_id": 4,
                                    "type": "HUMAN",
                                    "player_name": "user"
                                },
                                "game_players": [
                                    {
                                        "id": 10,
                                        "player_id": 4,
                                        "type": "HUMAN",
                                        "player_name": "user"
                                    },
                                    {
                                        "id": 9,
                                        "player_id": 3,
                                        "type": "BOT",
                                        "player_name": "user_BOT2"
                                    },
                                    {
                                        "id": 1,
                                        "player_id": 1,
                                        "type": "AI",
                                        "player_name": "user_AI2"
                                    },
                                    {
                                        "id": 8,
                                        "player_id": 2,
                                        "type": "BOT",
                                        "player_name": "user_BOT1"
                                    }
                                ],
                                "base_point": 5,
                                "fann_point": 2,
                                "dollar_per_point": 10
                            }
                            """)
            )
    )
    @PutMapping("/games/{game_id}/decide-east-player")
    ResponseEntity<GameDto> decideEastPlayer(@PathVariable(name = "game_id") Long gameId);
}
