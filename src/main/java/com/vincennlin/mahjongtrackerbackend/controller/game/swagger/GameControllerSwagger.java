package com.vincennlin.mahjongtrackerbackend.controller.game.swagger;

import com.vincennlin.mahjongtrackerbackend.constant.page.PageConstants;
import com.vincennlin.mahjongtrackerbackend.payload.game.dto.BoardDto;
import com.vincennlin.mahjongtrackerbackend.payload.game.dto.GameDto;
import com.vincennlin.mahjongtrackerbackend.payload.game.page.GamePageResponse;
import com.vincennlin.mahjongtrackerbackend.payload.game.request.CreateGameRequest;
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
            summary = "自動開始遊戲",
            description = "自動開始遊戲至準備捨牌的階段，並加入 3 位 BOT 玩家"
    )
    @ApiResponse(
            responseCode = "200",
            description = "成功自動開始遊戲",
            content = @Content(
                    mediaType = "application/json",
                    examples = @ExampleObject(value = """
                            {
                                "hand_id": 1,
                                "round_wind": "EAST",
                                "prevailing_wind": "EAST",
                                "active_game_player_id": 4,
                                "last_discarded_tile": null,
                                "status": "WAITING_FOR_DISCARD",
                                "acceptable_operations": [
                                    "DISCARD_TILE"
                                ],
                                "player_tiles": [
                                    {
                                        "id": 1,
                                        "hand_id": 1,
                                        "game_player_id": 4,
                                        "hand_tiles": {
                                            "id": 2,
                                            "tile_count": 17,
                                            "tiles_num": "一五 二五七八九 一一三三三六 南西 紅發",
                                            "tiles_sub": "萬萬 筒筒筒筒筒 條條條條條條 風風 中財",
                                            "tiles": [
                                                {
                                                    "id": 2,
                                                    "tile_name": "一萬"
                                                },
                                                {
                                                    "id": 17,
                                                    "tile_name": "五萬"
                                                },
                                                {
                                                    "id": 141,
                                                    "tile_name": "二筒"
                                                },
                                                {
                                                    "id": 18,
                                                    "tile_name": "五筒"
                                                },
                                                {
                                                    "id": 4,
                                                    "tile_name": "七筒"
                                                },
                                                {
                                                    "id": 50,
                                                    "tile_name": "八筒"
                                                },
                                                {
                                                    "id": 3,
                                                    "tile_name": "九筒"
                                                },
                                                {
                                                    "id": 35,
                                                    "tile_name": "一條"
                                                },
                                                {
                                                    "id": 144,
                                                    "tile_name": "一條"
                                                },
                                                {
                                                    "id": 20,
                                                    "tile_name": "三條"
                                                },
                                                {
                                                    "id": 49,
                                                    "tile_name": "三條"
                                                },
                                                {
                                                    "id": 65,
                                                    "tile_name": "三條"
                                                },
                                                {
                                                    "id": 19,
                                                    "tile_name": "六條"
                                                },
                                                {
                                                    "id": 34,
                                                    "tile_name": "南風"
                                                },
                                                {
                                                    "id": 51,
                                                    "tile_name": "西風"
                                                },
                                                {
                                                    "id": 52,
                                                    "tile_name": "紅中"
                                                },
                                                {
                                                    "id": 36,
                                                    "tile_name": "發財"
                                                }
                                            ],
                                            "player_id": 4
                                        },
                                        "exposed_tiles": {
                                            "id": 3,
                                            "tile_count": 2,
                                            "tiles_num": "竹 秋",
                                            "tiles_sub": " ",
                                            "tiles": [
                                                {
                                                    "id": 1,
                                                    "tile_name": "竹"
                                                },
                                                {
                                                    "id": 33,
                                                    "tile_name": "秋"
                                                }
                                            ],
                                            "player_id": 4
                                        },
                                        "discarded_tiles": {
                                            "id": 4,
                                            "tile_count": 0,
                                            "tiles_num": "",
                                            "tiles_sub": "",
                                            "tiles": [],
                                            "player_id": 4
                                        }
                                    },
                                    {
                                        "id": 2,
                                        "hand_id": 1,
                                        "game_player_id": 6,
                                        "hand_tiles": {
                                            "id": 5,
                                            "tile_count": 16,
                                            "tiles_num": "二四八八九 一一二八 二二八九 北 白白",
                                            "tiles_sub": "萬萬萬萬萬 筒筒筒筒 條條條條 風 板板",
                                            "tiles": [
                                                {
                                                    "id": 56,
                                                    "tile_name": "二萬"
                                                },
                                                {
                                                    "id": 7,
                                                    "tile_name": "四萬"
                                                },
                                                {
                                                    "id": 8,
                                                    "tile_name": "八萬"
                                                },
                                                {
                                                    "id": 38,
                                                    "tile_name": "八萬"
                                                },
                                                {
                                                    "id": 21,
                                                    "tile_name": "九萬"
                                                },
                                                {
                                                    "id": 39,
                                                    "tile_name": "一筒"
                                                },
                                                {
                                                    "id": 55,
                                                    "tile_name": "一筒"
                                                },
                                                {
                                                    "id": 5,
                                                    "tile_name": "二筒"
                                                },
                                                {
                                                    "id": 23,
                                                    "tile_name": "八筒"
                                                },
                                                {
                                                    "id": 22,
                                                    "tile_name": "二條"
                                                },
                                                {
                                                    "id": 54,
                                                    "tile_name": "二條"
                                                },
                                                {
                                                    "id": 24,
                                                    "tile_name": "八條"
                                                },
                                                {
                                                    "id": 6,
                                                    "tile_name": "九條"
                                                },
                                                {
                                                    "id": 143,
                                                    "tile_name": "北風"
                                                },
                                                {
                                                    "id": 37,
                                                    "tile_name": "白板"
                                                },
                                                {
                                                    "id": 53,
                                                    "tile_name": "白板"
                                                }
                                            ],
                                            "player_id": 6
                                        },
                                        "exposed_tiles": {
                                            "id": 6,
                                            "tile_count": 1,
                                            "tiles_num": "冬",
                                            "tiles_sub": "",
                                            "tiles": [
                                                {
                                                    "id": 40,
                                                    "tile_name": "冬"
                                                }
                                            ],
                                            "player_id": 6
                                        },
                                        "discarded_tiles": {
                                            "id": 7,
                                            "tile_count": 0,
                                            "tiles_num": "",
                                            "tiles_sub": "",
                                            "tiles": [],
                                            "player_id": 6
                                        }
                                    },
                                    {
                                        "id": 3,
                                        "hand_id": 1,
                                        "game_player_id": 5,
                                        "hand_tiles": {
                                            "id": 8,
                                            "tile_count": 16,
                                            "tiles_num": "一六八九九 一四六六八 四四六九 東 紅",
                                            "tiles_sub": "萬萬萬萬萬 筒筒筒筒筒 條條條條 風 中",
                                            "tiles": [
                                                {
                                                    "id": 57,
                                                    "tile_name": "一萬"
                                                },
                                                {
                                                    "id": 10,
                                                    "tile_name": "六萬"
                                                },
                                                {
                                                    "id": 25,
                                                    "tile_name": "八萬"
                                                },
                                                {
                                                    "id": 12,
                                                    "tile_name": "九萬"
                                                },
                                                {
                                                    "id": 26,
                                                    "tile_name": "九萬"
                                                },
                                                {
                                                    "id": 58,
                                                    "tile_name": "一筒"
                                                },
                                                {
                                                    "id": 28,
                                                    "tile_name": "四筒"
                                                },
                                                {
                                                    "id": 11,
                                                    "tile_name": "六筒"
                                                },
                                                {
                                                    "id": 27,
                                                    "tile_name": "六筒"
                                                },
                                                {
                                                    "id": 42,
                                                    "tile_name": "八筒"
                                                },
                                                {
                                                    "id": 9,
                                                    "tile_name": "四條"
                                                },
                                                {
                                                    "id": 44,
                                                    "tile_name": "四條"
                                                },
                                                {
                                                    "id": 60,
                                                    "tile_name": "六條"
                                                },
                                                {
                                                    "id": 41,
                                                    "tile_name": "九條"
                                                },
                                                {
                                                    "id": 43,
                                                    "tile_name": "東風"
                                                },
                                                {
                                                    "id": 59,
                                                    "tile_name": "紅中"
                                                }
                                            ],
                                            "player_id": 5
                                        },
                                        "exposed_tiles": {
                                            "id": 9,
                                            "tile_count": 0,
                                            "tiles_num": "",
                                            "tiles_sub": "",
                                            "tiles": [],
                                            "player_id": 5
                                        },
                                        "discarded_tiles": {
                                            "id": 10,
                                            "tile_count": 0,
                                            "tiles_num": "",
                                            "tiles_sub": "",
                                            "tiles": [],
                                            "player_id": 5
                                        }
                                    },
                                    {
                                        "id": 4,
                                        "hand_id": 1,
                                        "game_player_id": 3,
                                        "hand_tiles": {
                                            "id": 11,
                                            "tile_count": 16,
                                            "tiles_num": "二二三四四五六七 二四四七 五九 東西",
                                            "tiles_sub": "萬萬萬萬萬萬萬萬 筒筒筒筒 條條 風風",
                                            "tiles": [
                                                {
                                                    "id": 48,
                                                    "tile_name": "二萬"
                                                },
                                                {
                                                    "id": 142,
                                                    "tile_name": "二萬"
                                                },
                                                {
                                                    "id": 29,
                                                    "tile_name": "三萬"
                                                },
                                                {
                                                    "id": 14,
                                                    "tile_name": "四萬"
                                                },
                                                {
                                                    "id": 62,
                                                    "tile_name": "四萬"
                                                },
                                                {
                                                    "id": 32,
                                                    "tile_name": "五萬"
                                                },
                                                {
                                                    "id": 16,
                                                    "tile_name": "六萬"
                                                },
                                                {
                                                    "id": 140,
                                                    "tile_name": "七萬"
                                                },
                                                {
                                                    "id": 13,
                                                    "tile_name": "二筒"
                                                },
                                                {
                                                    "id": 46,
                                                    "tile_name": "四筒"
                                                },
                                                {
                                                    "id": 47,
                                                    "tile_name": "四筒"
                                                },
                                                {
                                                    "id": 45,
                                                    "tile_name": "七筒"
                                                },
                                                {
                                                    "id": 63,
                                                    "tile_name": "五條"
                                                },
                                                {
                                                    "id": 30,
                                                    "tile_name": "九條"
                                                },
                                                {
                                                    "id": 64,
                                                    "tile_name": "東風"
                                                },
                                                {
                                                    "id": 31,
                                                    "tile_name": "西風"
                                                }
                                            ],
                                            "player_id": 3
                                        },
                                        "exposed_tiles": {
                                            "id": 12,
                                            "tile_count": 2,
                                            "tiles_num": "梅 春",
                                            "tiles_sub": " ",
                                            "tiles": [
                                                {
                                                    "id": 15,
                                                    "tile_name": "梅"
                                                },
                                                {
                                                    "id": 61,
                                                    "tile_name": "春"
                                                }
                                            ],
                                            "player_id": 3
                                        },
                                        "discarded_tiles": {
                                            "id": 13,
                                            "tile_count": 0,
                                            "tiles_num": "",
                                            "tiles_sub": "",
                                            "tiles": [],
                                            "player_id": 3
                                        }
                                    }
                                ],
                                "wall_tiles": {
                                    "id": 1,
                                    "tile_count": 74,
                                    "tiles": [
                                        {
                                            "id": 66,
                                            "tile_name": "九條"
                                        },
                                        {
                                            "id": 67,
                                            "tile_name": "一條"
                                        },
                                        {
                                            "id": 68,
                                            "tile_name": "七筒"
                                        },
                                        {
                                            "id": 69,
                                            "tile_name": "六條"
                                        },
                                        {
                                            "id": 70,
                                            "tile_name": "八條"
                                        },
                                        {
                                            "id": 71,
                                            "tile_name": "北風"
                                        },
                                        {
                                            "id": 72,
                                            "tile_name": "七萬"
                                        },
                                        {
                                            "id": 73,
                                            "tile_name": "五筒"
                                        },
                                        {
                                            "id": 74,
                                            "tile_name": "二筒"
                                        },
                                        {
                                            "id": 75,
                                            "tile_name": "二條"
                                        },
                                        {
                                            "id": 76,
                                            "tile_name": "一萬"
                                        },
                                        {
                                            "id": 77,
                                            "tile_name": "三筒"
                                        },
                                        {
                                            "id": 78,
                                            "tile_name": "五萬"
                                        },
                                        {
                                            "id": 79,
                                            "tile_name": "五筒"
                                        },
                                        {
                                            "id": 80,
                                            "tile_name": "北風"
                                        },
                                        {
                                            "id": 81,
                                            "tile_name": "五條"
                                        },
                                        {
                                            "id": 82,
                                            "tile_name": "八條"
                                        },
                                        {
                                            "id": 83,
                                            "tile_name": "南風"
                                        },
                                        {
                                            "id": 84,
                                            "tile_name": "六條"
                                        },
                                        {
                                            "id": 85,
                                            "tile_name": "五條"
                                        },
                                        {
                                            "id": 86,
                                            "tile_name": "四條"
                                        },
                                        {
                                            "id": 87,
                                            "tile_name": "四萬"
                                        },
                                        {
                                            "id": 88,
                                            "tile_name": "八條"
                                        },
                                        {
                                            "id": 89,
                                            "tile_name": "二條"
                                        },
                                        {
                                            "id": 90,
                                            "tile_name": "七條"
                                        },
                                        {
                                            "id": 91,
                                            "tile_name": "夏"
                                        },
                                        {
                                            "id": 92,
                                            "tile_name": "五條"
                                        },
                                        {
                                            "id": 93,
                                            "tile_name": "三條"
                                        },
                                        {
                                            "id": 94,
                                            "tile_name": "白板"
                                        },
                                        {
                                            "id": 95,
                                            "tile_name": "北風"
                                        },
                                        {
                                            "id": 96,
                                            "tile_name": "南風"
                                        },
                                        {
                                            "id": 97,
                                            "tile_name": "九筒"
                                        },
                                        {
                                            "id": 98,
                                            "tile_name": "白板"
                                        },
                                        {
                                            "id": 99,
                                            "tile_name": "一條"
                                        },
                                        {
                                            "id": 100,
                                            "tile_name": "東風"
                                        },
                                        {
                                            "id": 101,
                                            "tile_name": "九萬"
                                        },
                                        {
                                            "id": 102,
                                            "tile_name": "二萬"
                                        },
                                        {
                                            "id": 103,
                                            "tile_name": "菊"
                                        },
                                        {
                                            "id": 104,
                                            "tile_name": "七筒"
                                        },
                                        {
                                            "id": 105,
                                            "tile_name": "五筒"
                                        },
                                        {
                                            "id": 106,
                                            "tile_name": "八萬"
                                        },
                                        {
                                            "id": 107,
                                            "tile_name": "六筒"
                                        },
                                        {
                                            "id": 108,
                                            "tile_name": "六萬"
                                        },
                                        {
                                            "id": 109,
                                            "tile_name": "東風"
                                        },
                                        {
                                            "id": 110,
                                            "tile_name": "七萬"
                                        },
                                        {
                                            "id": 111,
                                            "tile_name": "九筒"
                                        },
                                        {
                                            "id": 112,
                                            "tile_name": "七條"
                                        },
                                        {
                                            "id": 113,
                                            "tile_name": "三筒"
                                        },
                                        {
                                            "id": 114,
                                            "tile_name": "八筒"
                                        },
                                        {
                                            "id": 115,
                                            "tile_name": "西風"
                                        },
                                        {
                                            "id": 116,
                                            "tile_name": "六萬"
                                        },
                                        {
                                            "id": 117,
                                            "tile_name": "三筒"
                                        },
                                        {
                                            "id": 118,
                                            "tile_name": "一萬"
                                        },
                                        {
                                            "id": 119,
                                            "tile_name": "紅中"
                                        },
                                        {
                                            "id": 120,
                                            "tile_name": "六筒"
                                        },
                                        {
                                            "id": 121,
                                            "tile_name": "發財"
                                        },
                                        {
                                            "id": 122,
                                            "tile_name": "發財"
                                        },
                                        {
                                            "id": 123,
                                            "tile_name": "發財"
                                        },
                                        {
                                            "id": 124,
                                            "tile_name": "三萬"
                                        },
                                        {
                                            "id": 125,
                                            "tile_name": "七條"
                                        },
                                        {
                                            "id": 126,
                                            "tile_name": "三筒"
                                        },
                                        {
                                            "id": 127,
                                            "tile_name": "九筒"
                                        },
                                        {
                                            "id": 128,
                                            "tile_name": "四筒"
                                        },
                                        {
                                            "id": 129,
                                            "tile_name": "七萬"
                                        },
                                        {
                                            "id": 130,
                                            "tile_name": "西風"
                                        },
                                        {
                                            "id": 131,
                                            "tile_name": "蘭"
                                        },
                                        {
                                            "id": 132,
                                            "tile_name": "南風"
                                        },
                                        {
                                            "id": 133,
                                            "tile_name": "五萬"
                                        },
                                        {
                                            "id": 134,
                                            "tile_name": "三萬"
                                        },
                                        {
                                            "id": 135,
                                            "tile_name": "三萬"
                                        },
                                        {
                                            "id": 136,
                                            "tile_name": "七條"
                                        },
                                        {
                                            "id": 137,
                                            "tile_name": "一筒"
                                        },
                                        {
                                            "id": 138,
                                            "tile_name": "紅中"
                                        },
                                        {
                                            "id": 139,
                                            "tile_name": "四條"
                                        }
                                    ],
                                    "hand_id": 1
                                }
                            }
                            """)
            )
    )
    @PostMapping("/games/auto-start")
    ResponseEntity<BoardDto> autoStartGame(@Valid @RequestBody
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
