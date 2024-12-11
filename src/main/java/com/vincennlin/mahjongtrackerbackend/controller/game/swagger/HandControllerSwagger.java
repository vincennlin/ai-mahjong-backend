package com.vincennlin.mahjongtrackerbackend.controller.game.swagger;

import com.vincennlin.mahjongtrackerbackend.payload.game.dto.BoardDto;
import com.vincennlin.mahjongtrackerbackend.payload.game.dto.HandDto;
import com.vincennlin.mahjongtrackerbackend.payload.game.dto.PlayerViewDto;
import com.vincennlin.mahjongtrackerbackend.payload.game.request.ai.DiscardAdviceResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Tag(
        name = "Hand Controller",
        description = "遊戲中每手相關的 API，順序：出牌 -> （取消叫牌） -> 抓牌 -> 出牌⋯⋯」"
)
public interface HandControllerSwagger {

    @Operation(
            summary = "取得目前局",
            description = "取得目前局，在遊戲狀態 \"IN_PROGRESS\" 時可以呼叫此 API，取得目前局資訊。"
    )
    @ApiResponse(
            responseCode = "200",
            description = "成功取得目前局",
            content = @Content(
                    mediaType = "application/json",
                    examples = @ExampleObject(value = """
                            
                            """)
            )
    )
    @GetMapping("/games/{game_id}/hands/current")
    ResponseEntity<HandDto> getCurrentHandByGameId(@PathVariable(name = "game_id") Long gameId);

    @Operation(
            summary = "[FOR TEST] 取得所有牌面，測試用",
            description = "取得目前所有牌面，在遊戲狀態 \"IN_PROGRESS\" 時可以呼叫此 API，取得目前所有的牌的資訊。"
    )
    @ApiResponse(
            responseCode = "200",
            description = "成功取得目前牌面",
            content = @Content(
                    mediaType = "application/json",
                    examples = @ExampleObject(value = """
                            
                            """)
            )
    )
    @GetMapping("/games/{game_id}/boards")
    ResponseEntity<BoardDto> getBoardByGameId(@PathVariable(name = "game_id") Long gameId);

    @Operation(
            summary = "[NEW] 取得目前玩家視角",
            description = "取得目前玩家視角，在遊戲狀態 \"IN_PROGRESS\" 時可以呼叫此 API，取得目前玩家視角。"
    )
    @ApiResponse(
            responseCode = "200",
            description = "成功取得目前玩家視角",
            content = @Content(
                    mediaType = "application/json",
                    examples = @ExampleObject(value = """
                            {
                                "game_id": 1,
                                "hand_id": 1,
                                "round_wind": "EAST",
                                "prevailing_wind": "EAST",
                                "active_game_player_id": 4,
                                "last_discarded_tile": {
                                    "id": 44,
                                    "tile_id": 32,
                                    "tile_name": "發財"
                                },
                                "hand_status": "WAITING_FOR_DRAW",
                                "game_player_status": "WAITING",
                                "acceptable_operations": [],
                                "player_tile": {
                                    "id": 3,
                                    "hand_id": 1,
                                    "game_player_id": 2,
                                    "hand_tiles": {
                                        "id": 8,
                                        "tile_count": 13,
                                        "tiles_num": "二二四五七 四九 二六六 東西 紅",
                                        "tiles_sub": "萬萬萬萬萬 筒筒 條條條 風風 中",
                                        "tiles": [
                                            {
                                                "id": 26,
                                                "tile_id": 1,
                                                "tile_name": "二萬"
                                            },
                                            {
                                                "id": 60,
                                                "tile_id": 1,
                                                "tile_name": "二萬"
                                            },
                                            {
                                                "id": 59,
                                                "tile_id": 3,
                                                "tile_name": "四萬"
                                            },
                                            {
                                                "id": 12,
                                                "tile_id": 4,
                                                "tile_name": "五萬"
                                            },
                                            {
                                                "id": 57,
                                                "tile_id": 6,
                                                "tile_name": "七萬"
                                            },
                                            {
                                                "id": 10,
                                                "tile_id": 12,
                                                "tile_name": "四筒"
                                            },
                                            {
                                                "id": 41,
                                                "tile_id": 17,
                                                "tile_name": "九筒"
                                            },
                                            {
                                                "id": 42,
                                                "tile_id": 19,
                                                "tile_name": "二條"
                                            },
                                            {
                                                "id": 25,
                                                "tile_id": 23,
                                                "tile_name": "六條"
                                            },
                                            {
                                                "id": 43,
                                                "tile_id": 23,
                                                "tile_name": "六條"
                                            },
                                            {
                                                "id": 58,
                                                "tile_id": 27,
                                                "tile_name": "東風"
                                            },
                                            {
                                                "id": 11,
                                                "tile_id": 29,
                                                "tile_name": "西風"
                                            },
                                            {
                                                "id": 9,
                                                "tile_id": 31,
                                                "tile_name": "紅中"
                                            }
                                        ],
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
                                    "exposed_tile_list": [
                                        {
                                            "id": 14,
                                            "tile_count": 3,
                                            "tiles_num": "三三三",
                                            "tiles_sub": "條條條",
                                            "tiles": [
                                                {
                                                    "id": 4,
                                                    "tile_id": 20,
                                                    "tile_name": "三條"
                                                },
                                                {
                                                    "id": 27,
                                                    "tile_id": 20,
                                                    "tile_name": "三條"
                                                },
                                                {
                                                    "id": 28,
                                                    "tile_id": 20,
                                                    "tile_name": "三條"
                                                }
                                            ],
                                            "player_id": 2,
                                            "meld_type": "PONG"
                                        }
                                    ],
                                    "discarded_tiles": {
                                        "id": 10,
                                        "tile_count": 1,
                                        "tiles_num": "發",
                                        "tiles_sub": "財",
                                        "tiles": [
                                            {
                                                "id": 44,
                                                "tile_id": 32,
                                                "tile_name": "發財"
                                            }
                                        ],
                                        "player_id": 2
                                    }
                                },
                                "downwind_player_tile": {
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
                                "opposite_player_tile": {
                                    "id": 1,
                                    "hand_id": 1,
                                    "game_player_id": 3,
                                    "hand_tiles": {
                                        "id": 2,
                                        "tile_count": 16,
                                        "tiles_num": "****************",
                                        "tiles_sub": "****************",
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
                                "upwind_player_tile": {
                                    "id": 2,
                                    "hand_id": 1,
                                    "game_player_id": 1,
                                    "hand_tiles": {
                                        "id": 5,
                                        "tile_count": 16,
                                        "tiles_num": "****************",
                                        "tiles_sub": "****************",
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
    @GetMapping("/games/{game_id}/current-player-view")
    ResponseEntity<PlayerViewDto> getCurrentPlayerViewByGameId(@PathVariable(name = "game_id") Long gameId);

//    @GetMapping("/games/{game_id}/game-players/{game_player_id}/player-view")
//    ResponseEntity<PlayerViewDto> getPlayerViewByGameIdAndGamePlayerId(@PathVariable(name = "game_id") Long gameId,
//                                                                              @PathVariable(name = "game_player_id") Long gamePlayerId);

//    @Operation(
//            summary = "開始新的一手",
//            description = "開始新的一手，在遊戲狀態 \"READY_TO_START_NEW_ROUND\" 或 \"READY_TO_START_NEW_HAND\" 時可以呼叫此 API，開始後將遊戲狀態改為 \"READY_TO_INITIALIZE_WALL_TILES\" 。"
//    )
//    @ApiResponse(
//            responseCode = "201",
//            description = "成功開始新的一手",
//            content = @Content(
//                    mediaType = "application/json",
//                    examples = @ExampleObject(value = """
//
//                            """)
//            )
//    )
//    @PostMapping("/games/{game_id}/hands")
//    ResponseEntity<HandDto> startNewHand(@PathVariable(name = "game_id") Long gameId);

//    @Operation(
//            summary = "初始化牌牆",
//            description = "初始化牌牆，在遊戲狀態 \"READY_TO_INITIALIZE_WALL_TILES\" 時可以呼叫此 API，初始化後將遊戲狀態改為 \"IN_PROGRESS\"，將新的一局狀態設為 \"READY_TO_ROLL_DICE\" 。"
//    )
//    @ApiResponse(
//            responseCode = "200",
//            description = "成功初始化牌牆",
//            content = @Content(
//                    mediaType = "application/json",
//                    examples = @ExampleObject(value = """
//
//                            """)
//            )
//    )
//    @PostMapping("/games/{game_id}/hands/initialize-wall-tiles")
//    ResponseEntity<BoardDto> initializeWallTiles(@PathVariable(name = "game_id") Long gameId);
//
//    @Operation(
//            summary = "擲骰子",
//            description = "擲骰子，在局狀態 \"READY_TO_ROLL_DICE\" 時可以呼叫此 API，擲骰子後將局狀態改為 \"READY_TO_DEAL_TILES\" 。"
//    )
//    @ApiResponse(
//            responseCode = "200",
//            description = "成功擲骰子",
//            content = @Content(
//                    mediaType = "application/json",
//                    examples = @ExampleObject(value = """
//
//                            """)
//            )
//    )
//    @PostMapping("/games/{game_id}/hands/roll-dice")
//    ResponseEntity<HandDto> rollDice(@PathVariable(name = "game_id") Long gameId);
//
//    @Operation(
//            summary = "初始抓牌",
//            description = "初始抓牌，在局狀態 \"READY_TO_DEAL_TILES\" 時可以呼叫此 API，發牌後將局狀態改為 \"FINISHED_DEALING\" 。"
//    )
//    @ApiResponse(
//            responseCode = "200",
//            description = "成功擲骰子",
//            content = @Content(
//                    mediaType = "application/json",
//                    examples = @ExampleObject(value = """
//
//                            """)
//            )
//    )
//    @PostMapping("/games/{game_id}/hands/deal-tiles")
//    ResponseEntity<BoardDto> dealTiles(@PathVariable(name = "game_id") Long gameId);
//
//    @Operation(
//            summary = "開門",
//            description = "莊家開門，在局狀態 \"FINISHED_DEALING\" 時可以呼叫此 API，開門後將遊戲狀態改為 \"FINISHED_BREAKING_WALL\" 。"
//    )
//    @ApiResponse(
//            responseCode = "200",
//            description = "成功擲骰子",
//            content = @Content(
//                    mediaType = "application/json",
//                    examples = @ExampleObject(value = """
//
//                            """)
//            )
//    )
//    @PostMapping("/games/{game_id}/hands/break-wall")
//    ResponseEntity<BoardDto> breakWall(@PathVariable(name = "game_id") Long gameId);
//
//    @Operation(
//            summary = "初始補花",
//            description = "莊家與玩家補花，在局狀態 \"FINISHED_BREAKING_WALL\" 時可以呼叫此 API，開局後將遊戲狀態改為 \"PLAYING\" 。"
//    )
//    @ApiResponse(
//            responseCode = "200",
//            description = "成功初始補花",
//            content = @Content(
//                    mediaType = "application/json",
//                    examples = @ExampleObject(value = """
//
//                            """)
//            )
//    )
//    @PostMapping("/games/{game_id}/hands/initial-foul-hand")
//    ResponseEntity<PlayerViewDto> initialFoulHand(@PathVariable(name = "game_id") Long gameId);

    @Operation(
            summary = "[NEW] 出牌",
            description = "玩家出牌，以牌 id 將牌從手牌中移除，加入到棄牌區。"
    )
    @ApiResponse(
            responseCode = "200",
            description = "成功出牌",
            content = @Content(
                    mediaType = "application/json",
                    examples = @ExampleObject(value = """
                            {
                                 "game_id": 1,
                                 "hand_id": 1,
                                 "round_wind": "EAST",
                                 "prevailing_wind": "EAST",
                                 "active_game_player_id": 3,
                                 "last_discarded_tile": {
                                     "id": 4,
                                     "tile_id": 20,
                                     "tile_name": "三條"
                                 },
                                 "hand_status": "WAITING_FOR_CALL",
                                 "game_player_status": "WAITING",
                                 "acceptable_operations": [],
                                 "player_tile": {
                                     "id": 1,
                                     "hand_id": 1,
                                     "game_player_id": 3,
                                     "hand_tiles": {
                                         "id": 2,
                                         "tile_count": 16,
                                         "tiles_num": "五六八 一一二七八 四五八八九 東北 白",
                                         "tiles_sub": "萬萬萬 筒筒筒筒筒 條條條條條 風風 板",
                                         "tiles": [
                                             {
                                                 "id": 20,
                                                 "tile_id": 4,
                                                 "tile_name": "五萬"
                                             },
                                             {
                                                 "id": 3,
                                                 "tile_id": 5,
                                                 "tile_name": "六萬"
                                             },
                                             {
                                                 "id": 17,
                                                 "tile_id": 7,
                                                 "tile_name": "八萬"
                                             },
                                             {
                                                 "id": 50,
                                                 "tile_id": 9,
                                                 "tile_name": "一筒"
                                             },
                                             {
                                                 "id": 144,
                                                 "tile_id": 9,
                                                 "tile_name": "一筒"
                                             },
                                             {
                                                 "id": 2,
                                                 "tile_id": 10,
                                                 "tile_name": "二筒"
                                             },
                                             {
                                                 "id": 36,
                                                 "tile_id": 15,
                                                 "tile_name": "七筒"
                                             },
                                             {
                                                 "id": 51,
                                                 "tile_id": 16,
                                                 "tile_name": "八筒"
                                             },
                                             {
                                                 "id": 49,
                                                 "tile_id": 21,
                                                 "tile_name": "四條"
                                             },
                                             {
                                                 "id": 1,
                                                 "tile_id": 22,
                                                 "tile_name": "五條"
                                             },
                                             {
                                                 "id": 19,
                                                 "tile_id": 25,
                                                 "tile_name": "八條"
                                             },
                                             {
                                                 "id": 65,
                                                 "tile_id": 25,
                                                 "tile_name": "八條"
                                             },
                                             {
                                                 "id": 35,
                                                 "tile_id": 26,
                                                 "tile_name": "九條"
                                             },
                                             {
                                                 "id": 52,
                                                 "tile_id": 27,
                                                 "tile_name": "東風"
                                             },
                                             {
                                                 "id": 34,
                                                 "tile_id": 30,
                                                 "tile_name": "北風"
                                             },
                                             {
                                                 "id": 33,
                                                 "tile_id": 33,
                                                 "tile_name": "白板"
                                             }
                                         ],
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
                                         "tile_count": 1,
                                         "tiles_num": "三",
                                         "tiles_sub": "條",
                                         "tiles": [
                                             {
                                                 "id": 4,
                                                 "tile_id": 20,
                                                 "tile_name": "三條"
                                             }
                                         ],
                                         "player_id": 3
                                     }
                                 },
                                 "downwind_player_tile": {
                                     "id": 2,
                                     "hand_id": 1,
                                     "game_player_id": 1,
                                     "hand_tiles": {
                                         "id": 5,
                                         "tile_count": 16,
                                         "tiles_num": "****************",
                                         "tiles_sub": "****************",
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
                                 "opposite_player_tile": {
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
                                 "upwind_player_tile": {
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
    @PostMapping("/games/{game_id}/discard/{board_tile_id}")
    ResponseEntity<PlayerViewDto> discardTile(@PathVariable(name = "game_id") Long gameId,
                                                @PathVariable(name = "board_tile_id") Long boardTileId);


//    @Operation(
//            summary = "以指定的玩家 id 的身份捨牌",
//            description = "玩家出牌，以牌 id 將牌從手牌中移除，加入到棄牌區。"
//    )
//    @ApiResponse(
//            responseCode = "200",
//            description = "成功出牌",
//            content = @Content(
//                    mediaType = "application/json",
//                    examples = @ExampleObject(value = """
//
//                            """)
//            )
//    )
//    @PostMapping("/games/{game_id}/game-players/{game_player_id}/discard/{board_tile_id}")
//    ResponseEntity<PlayerViewDto> discardTileByGamePlayerId(@PathVariable(name = "game_id") Long gameId,
//                                                              @PathVariable(name = "game_player_id") Long gamePlayerId,
//                                                              @PathVariable(name = "board_tile_id") Long boardTileId);


    @Operation(
            summary = "[NEW] 取消叫牌",
            description = "取消叫牌，當玩家在 \"THINKING_FOR_CALL\" 狀態時可以取消叫牌。"
    )
    @ApiResponse(
            responseCode = "200",
            description = "成功取消叫牌",
            content = @Content(
                    mediaType = "application/json",
                    examples = @ExampleObject(value = """
                            {
                                "game_id": 1,
                                "hand_id": 1,
                                "round_wind": "EAST",
                                "prevailing_wind": "EAST",
                                "active_game_player_id": 4,
                                "last_discarded_tile": {
                                    "id": 13,
                                    "tile_id": 1,
                                    "tile_name": "二萬"
                                },
                                "hand_status": "WAITING_FOR_DRAW",
                                "game_player_status": "WAITING",
                                "acceptable_operations": [],
                                "player_tile": {
                                    "id": 3,
                                    "hand_id": 1,
                                    "game_player_id": 2,
                                    "hand_tiles": {
                                        "id": 8,
                                        "tile_count": 13,
                                        "tiles_num": "二二四五七 四九 二六六 東西 紅",
                                        "tiles_sub": "萬萬萬萬萬 筒筒 條條條 風風 中",
                                        "tiles": [
                                            {
                                                "id": 26,
                                                "tile_id": 1,
                                                "tile_name": "二萬"
                                            },
                                            {
                                                "id": 60,
                                                "tile_id": 1,
                                                "tile_name": "二萬"
                                            },
                                            {
                                                "id": 59,
                                                "tile_id": 3,
                                                "tile_name": "四萬"
                                            },
                                            {
                                                "id": 12,
                                                "tile_id": 4,
                                                "tile_name": "五萬"
                                            },
                                            {
                                                "id": 57,
                                                "tile_id": 6,
                                                "tile_name": "七萬"
                                            },
                                            {
                                                "id": 10,
                                                "tile_id": 12,
                                                "tile_name": "四筒"
                                            },
                                            {
                                                "id": 41,
                                                "tile_id": 17,
                                                "tile_name": "九筒"
                                            },
                                            {
                                                "id": 42,
                                                "tile_id": 19,
                                                "tile_name": "二條"
                                            },
                                            {
                                                "id": 25,
                                                "tile_id": 23,
                                                "tile_name": "六條"
                                            },
                                            {
                                                "id": 43,
                                                "tile_id": 23,
                                                "tile_name": "六條"
                                            },
                                            {
                                                "id": 58,
                                                "tile_id": 27,
                                                "tile_name": "東風"
                                            },
                                            {
                                                "id": 11,
                                                "tile_id": 29,
                                                "tile_name": "西風"
                                            },
                                            {
                                                "id": 9,
                                                "tile_id": 31,
                                                "tile_name": "紅中"
                                            }
                                        ],
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
                                    "exposed_tile_list": [
                                        {
                                            "id": 14,
                                            "tile_count": 3,
                                            "tiles_num": "三三三",
                                            "tiles_sub": "條條條",
                                            "tiles": [
                                                {
                                                    "id": 4,
                                                    "tile_id": 20,
                                                    "tile_name": "三條"
                                                },
                                                {
                                                    "id": 27,
                                                    "tile_id": 20,
                                                    "tile_name": "三條"
                                                },
                                                {
                                                    "id": 28,
                                                    "tile_id": 20,
                                                    "tile_name": "三條"
                                                }
                                            ],
                                            "player_id": 2,
                                            "meld_type": "PONG"
                                        }
                                    ],
                                    "discarded_tiles": {
                                        "id": 10,
                                        "tile_count": 1,
                                        "tiles_num": "發",
                                        "tiles_sub": "財",
                                        "tiles": [
                                            {
                                                "id": 44,
                                                "tile_id": 32,
                                                "tile_name": "發財"
                                            }
                                        ],
                                        "player_id": 2
                                    }
                                },
                                "downwind_player_tile": {
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
                                        "tile_count": 1,
                                        "tiles_num": "二",
                                        "tiles_sub": "萬",
                                        "tiles": [
                                            {
                                                "id": 13,
                                                "tile_id": 1,
                                                "tile_name": "二萬"
                                            }
                                        ],
                                        "player_id": 4
                                    }
                                },
                                "opposite_player_tile": {
                                    "id": 1,
                                    "hand_id": 1,
                                    "game_player_id": 3,
                                    "hand_tiles": {
                                        "id": 2,
                                        "tile_count": 16,
                                        "tiles_num": "****************",
                                        "tiles_sub": "****************",
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
                                "upwind_player_tile": {
                                    "id": 2,
                                    "hand_id": 1,
                                    "game_player_id": 1,
                                    "hand_tiles": {
                                        "id": 5,
                                        "tile_count": 16,
                                        "tiles_num": "****************",
                                        "tiles_sub": "****************",
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
                                "wall_tiles": {
                                    "id": 1,
                                    "tile_count": 76,
                                    "tiles_num": "****************************************************************************",
                                    "tiles_sub": "****************************************************************************",
                                    "hand_id": 1
                                }
                            }
                            """)
            )
    )
    @PostMapping("/games/{game_id}/cancel-for-call")
    ResponseEntity<PlayerViewDto> cancelForCall(@PathVariable(name = "game_id") Long gameId);

    @Operation(
            summary = "[NEW] 抓牌",
            description = "玩家抓牌，從牌牆中抓一張牌，加入到手牌中。"
    )
    @ApiResponse(
            responseCode = "200",
            description = "成功抓牌",
            content = @Content(
                    mediaType = "application/json",
                    examples = @ExampleObject(value = """
                            {
                                "game_id": 1,
                                "hand_id": 1,
                                "round_wind": "EAST",
                                "prevailing_wind": "EAST",
                                "active_game_player_id": 4,
                                "last_discarded_tile": {
                                    "id": 44,
                                    "tile_id": 32,
                                    "tile_name": "發財"
                                },
                                "hand_status": "WAITING_FOR_DISCARD",
                                "game_player_status": "THINKING_FOR_DISCARD",
                                "acceptable_operations": [
                                    "CALL_FOR_CONCEALED_KONG",
                                    "CALL_FOR_ADDED_KONG",
                                    "CALL_FOR_SELF_DRAW",
                                    "DISCARD"
                                ],
                                "player_tile": {
                                    "id": 4,
                                    "hand_id": 1,
                                    "game_player_id": 4,
                                    "hand_tiles": {
                                        "id": 11,
                                        "tile_count": 17,
                                        "tiles_num": "一一二二三五 二二三四五六 二四五七 北",
                                        "tiles_sub": "萬萬萬萬萬萬 筒筒筒筒筒筒 條條條條 風",
                                        "tiles": [
                                            {
                                                "id": 29,
                                                "tile_id": 0,
                                                "tile_name": "一萬"
                                            },
                                            {
                                                "id": 61,
                                                "tile_id": 0,
                                                "tile_name": "一萬"
                                            },
                                            {
                                                "id": 13,
                                                "tile_id": 1,
                                                "tile_name": "二萬"
                                            },
                                            {
                                                "id": 48,
                                                "tile_id": 1,
                                                "tile_name": "二萬"
                                            },
                                            {
                                                "id": 45,
                                                "tile_id": 2,
                                                "tile_name": "三萬"
                                            },
                                            {
                                                "id": 63,
                                                "tile_id": 4,
                                                "tile_name": "五萬"
                                            },
                                            {
                                                "id": 46,
                                                "tile_id": 10,
                                                "tile_name": "二筒"
                                            },
                                            {
                                                "id": 66,
                                                "tile_id": 10,
                                                "tile_name": "二筒"
                                            },
                                            {
                                                "id": 14,
                                                "tile_id": 11,
                                                "tile_name": "三筒"
                                            },
                                            {
                                                "id": 31,
                                                "tile_id": 12,
                                                "tile_name": "四筒"
                                            },
                                            {
                                                "id": 30,
                                                "tile_id": 13,
                                                "tile_name": "五筒"
                                            },
                                            {
                                                "id": 16,
                                                "tile_id": 14,
                                                "tile_name": "六筒"
                                            },
                                            {
                                                "id": 15,
                                                "tile_id": 19,
                                                "tile_name": "二條"
                                            },
                                            {
                                                "id": 32,
                                                "tile_id": 21,
                                                "tile_name": "四條"
                                            },
                                            {
                                                "id": 64,
                                                "tile_id": 22,
                                                "tile_name": "五條"
                                            },
                                            {
                                                "id": 47,
                                                "tile_id": 24,
                                                "tile_name": "七條"
                                            },
                                            {
                                                "id": 62,
                                                "tile_id": 30,
                                                "tile_name": "北風"
                                            }
                                        ],
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
                                "downwind_player_tile": {
                                    "id": 1,
                                    "hand_id": 1,
                                    "game_player_id": 3,
                                    "hand_tiles": {
                                        "id": 2,
                                        "tile_count": 16,
                                        "tiles_num": "****************",
                                        "tiles_sub": "****************",
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
                                "opposite_player_tile": {
                                    "id": 2,
                                    "hand_id": 1,
                                    "game_player_id": 1,
                                    "hand_tiles": {
                                        "id": 5,
                                        "tile_count": 16,
                                        "tiles_num": "****************",
                                        "tiles_sub": "****************",
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
                                "upwind_player_tile": {
                                    "id": 3,
                                    "hand_id": 1,
                                    "game_player_id": 2,
                                    "hand_tiles": {
                                        "id": 8,
                                        "tile_count": 13,
                                        "tiles_num": "*************",
                                        "tiles_sub": "*************",
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
                                    "exposed_tile_list": [
                                        {
                                            "id": 14,
                                            "tile_count": 3,
                                            "tiles_num": "三三三",
                                            "tiles_sub": "條條條",
                                            "tiles": [
                                                {
                                                    "id": 4,
                                                    "tile_id": 20,
                                                    "tile_name": "三條"
                                                },
                                                {
                                                    "id": 27,
                                                    "tile_id": 20,
                                                    "tile_name": "三條"
                                                },
                                                {
                                                    "id": 28,
                                                    "tile_id": 20,
                                                    "tile_name": "三條"
                                                }
                                            ],
                                            "player_id": 2,
                                            "meld_type": "PONG"
                                        }
                                    ],
                                    "discarded_tiles": {
                                        "id": 10,
                                        "tile_count": 1,
                                        "tiles_num": "發",
                                        "tiles_sub": "財",
                                        "tiles": [
                                            {
                                                "id": 44,
                                                "tile_id": 32,
                                                "tile_name": "發財"
                                            }
                                        ],
                                        "player_id": 2
                                    }
                                },
                                "wall_tiles": {
                                    "id": 1,
                                    "tile_count": 76,
                                    "tiles_num": "****************************************************************************",
                                    "tiles_sub": "****************************************************************************",
                                    "hand_id": 1
                                }
                            }
                            """)
            )
    )
    @PostMapping("/games/{game_id}/draw-tile")
    ResponseEntity<PlayerViewDto> drawTile(@PathVariable(name = "game_id") Long gameId);

    @Operation(
            summary = "[NEW] 取得出牌建議",
            description = "取得出牌建議，當玩家在 \"THINKING_FOR_DISCARD\" 狀態時可以取得出牌建議。"
    )
    @ApiResponse(
            responseCode = "200",
            description = "成功取得出牌建議",
            content = @Content(
                    mediaType = "application/json",
                    examples = @ExampleObject(value = """
                            {
                                "discard_tile": "白板",
                                "discard_reason": "白板為孤張字牌，且目前沒有其他搭子的機會，建議捨去以降低手牌的複雜度。"
                            }
                            """)
            )
    )
    @PostMapping("/games/{game_id}/generate-discard-advice")
    ResponseEntity<DiscardAdviceResponse> generateDiscardAdvice(@PathVariable(name = "game_id") Long gameId);

    @Operation(
            summary = "[NEW] 碰牌",
            description = "碰牌，當玩家在 \"THINKING_FOR_CALL\" 狀態時可以碰牌。"
    )
    @ApiResponse(
            responseCode = "200",
            description = "成功碰牌",
            content = @Content(
                    mediaType = "application/json",
                    examples = @ExampleObject(value = """
                            {
                                "game_id": 1,
                                "hand_id": 1,
                                "round_wind": "EAST",
                                "prevailing_wind": "EAST",
                                "active_game_player_id": 2,
                                "last_discarded_tile": {
                                    "id": 4,
                                    "tile_id": 20,
                                    "tile_name": "三條"
                                },
                                "hand_status": "WAITING_FOR_DISCARD",
                                "game_player_status": "THINKING_FOR_DISCARD",
                                "acceptable_operations": [
                                    "CALL_FOR_CONCEALED_KONG",
                                    "CALL_FOR_ADDED_KONG",
                                    "CALL_FOR_SELF_DRAW",
                                    "DISCARD"
                                ],
                                "player_tile": {
                                    "id": 3,
                                    "hand_id": 1,
                                    "game_player_id": 2,
                                    "hand_tiles": {
                                        "id": 8,
                                        "tile_count": 14,
                                        "tiles_num": "二二四五七 四九 二六六 東西 紅發",
                                        "tiles_sub": "萬萬萬萬萬 筒筒 條條條 風風 中財",
                                        "tiles": [
                                            {
                                                "id": 26,
                                                "tile_id": 1,
                                                "tile_name": "二萬"
                                            },
                                            {
                                                "id": 60,
                                                "tile_id": 1,
                                                "tile_name": "二萬"
                                            },
                                            {
                                                "id": 59,
                                                "tile_id": 3,
                                                "tile_name": "四萬"
                                            },
                                            {
                                                "id": 12,
                                                "tile_id": 4,
                                                "tile_name": "五萬"
                                            },
                                            {
                                                "id": 57,
                                                "tile_id": 6,
                                                "tile_name": "七萬"
                                            },
                                            {
                                                "id": 10,
                                                "tile_id": 12,
                                                "tile_name": "四筒"
                                            },
                                            {
                                                "id": 41,
                                                "tile_id": 17,
                                                "tile_name": "九筒"
                                            },
                                            {
                                                "id": 42,
                                                "tile_id": 19,
                                                "tile_name": "二條"
                                            },
                                            {
                                                "id": 25,
                                                "tile_id": 23,
                                                "tile_name": "六條"
                                            },
                                            {
                                                "id": 43,
                                                "tile_id": 23,
                                                "tile_name": "六條"
                                            },
                                            {
                                                "id": 58,
                                                "tile_id": 27,
                                                "tile_name": "東風"
                                            },
                                            {
                                                "id": 11,
                                                "tile_id": 29,
                                                "tile_name": "西風"
                                            },
                                            {
                                                "id": 9,
                                                "tile_id": 31,
                                                "tile_name": "紅中"
                                            },
                                            {
                                                "id": 44,
                                                "tile_id": 32,
                                                "tile_name": "發財"
                                            }
                                        ],
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
                                    "exposed_tile_list": [
                                        {
                                            "id": 14,
                                            "tile_count": 3,
                                            "tiles_num": "三三三",
                                            "tiles_sub": "條條條",
                                            "tiles": [
                                                {
                                                    "id": 27,
                                                    "tile_id": 20,
                                                    "tile_name": "三條"
                                                },
                                                {
                                                    "id": 4,
                                                    "tile_id": 20,
                                                    "tile_name": "三條"
                                                },
                                                {
                                                    "id": 28,
                                                    "tile_id": 20,
                                                    "tile_name": "三條"
                                                }
                                            ],
                                            "player_id": 2,
                                            "meld_type": "PONG"
                                        }
                                    ],
                                    "discarded_tiles": {
                                        "id": 10,
                                        "tile_count": 0,
                                        "tiles_num": "",
                                        "tiles_sub": "",
                                        "tiles": [],
                                        "player_id": 2
                                    }
                                },
                                "downwind_player_tile": {
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
                                "opposite_player_tile": {
                                    "id": 1,
                                    "hand_id": 1,
                                    "game_player_id": 3,
                                    "hand_tiles": {
                                        "id": 2,
                                        "tile_count": 16,
                                        "tiles_num": "****************",
                                        "tiles_sub": "****************",
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
                                "upwind_player_tile": {
                                    "id": 2,
                                    "hand_id": 1,
                                    "game_player_id": 1,
                                    "hand_tiles": {
                                        "id": 5,
                                        "tile_count": 16,
                                        "tiles_num": "****************",
                                        "tiles_sub": "****************",
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
    @PostMapping("/games/{game_id}/pong-tile")
    ResponseEntity<PlayerViewDto> pongTile(@PathVariable(name = "game_id") Long gameId);
}
