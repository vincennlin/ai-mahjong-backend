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
                                "game_id": 5,
                                "hand_id": 4,
                                "round_wind": "EAST",
                                "prevailing_wind": "EAST",
                                "active_game_player_id": 17,
                                "last_discarded_tile": null,
                                "status": "WAITING_FOR_DISCARD",
                                "acceptable_operations": [
                                    "DISCARD_TILE"
                                ],
                                "player_tiles": [
                                    {
                                        "id": 13,
                                        "hand_id": 4,
                                        "game_player_id": 17,
                                        "hand_tiles": {
                                            "id": 41,
                                            "tile_count": 17,
                                            "tiles_num": "一三五七八 四六九 一九九 南 發白白白白",
                                            "tiles_sub": "萬萬萬萬萬 筒筒筒 條條條 風 財板板板板",
                                            "tiles": [
                                                {
                                                    "id": 452,
                                                    "tile_id": 0,
                                                    "tile_name": "一萬"
                                                },
                                                {
                                                    "id": 467,
                                                    "tile_id": 2,
                                                    "tile_name": "三萬"
                                                },
                                                {
                                                    "id": 483,
                                                    "tile_id": 4,
                                                    "tile_name": "五萬"
                                                },
                                                {
                                                    "id": 433,
                                                    "tile_id": 6,
                                                    "tile_name": "七萬"
                                                },
                                                {
                                                    "id": 484,
                                                    "tile_id": 7,
                                                    "tile_name": "八萬"
                                                },
                                                {
                                                    "id": 481,
                                                    "tile_id": 12,
                                                    "tile_name": "四筒"
                                                },
                                                {
                                                    "id": 576,
                                                    "tile_id": 14,
                                                    "tile_name": "六筒"
                                                },
                                                {
                                                    "id": 466,
                                                    "tile_id": 17,
                                                    "tile_name": "九筒"
                                                },
                                                {
                                                    "id": 451,
                                                    "tile_id": 18,
                                                    "tile_name": "一條"
                                                },
                                                {
                                                    "id": 436,
                                                    "tile_id": 26,
                                                    "tile_name": "九條"
                                                },
                                                {
                                                    "id": 497,
                                                    "tile_id": 26,
                                                    "tile_name": "九條"
                                                },
                                                {
                                                    "id": 482,
                                                    "tile_id": 28,
                                                    "tile_name": "南風"
                                                },
                                                {
                                                    "id": 434,
                                                    "tile_id": 32,
                                                    "tile_name": "發財"
                                                },
                                                {
                                                    "id": 435,
                                                    "tile_id": 33,
                                                    "tile_name": "白板"
                                                },
                                                {
                                                    "id": 449,
                                                    "tile_id": 33,
                                                    "tile_name": "白板"
                                                },
                                                {
                                                    "id": 465,
                                                    "tile_id": 33,
                                                    "tile_name": "白板"
                                                },
                                                {
                                                    "id": 468,
                                                    "tile_id": 33,
                                                    "tile_name": "白板"
                                                }
                                            ],
                                            "player_id": 17
                                        },
                                        "exposed_tiles": {
                                            "id": 42,
                                            "tile_count": 1,
                                            "tiles_num": "梅",
                                            "tiles_sub": "",
                                            "tiles": [
                                                {
                                                    "id": 450,
                                                    "tile_id": 38,
                                                    "tile_name": "梅"
                                                }
                                            ],
                                            "player_id": 17
                                        },
                                        "discarded_tiles": {
                                            "id": 43,
                                            "tile_count": 0,
                                            "tiles_num": "",
                                            "tiles_sub": "",
                                            "tiles": [],
                                            "player_id": 17
                                        }
                                    },
                                    {
                                        "id": 14,
                                        "hand_id": 4,
                                        "game_player_id": 18,
                                        "hand_tiles": {
                                            "id": 44,
                                            "tile_count": 16,
                                            "tiles_num": "三四四六八九 四五九九 三四五八 北 紅",
                                            "tiles_sub": "萬萬萬萬萬萬 筒筒筒筒 條條條條 風 中",
                                            "tiles": [
                                                {
                                                    "id": 470,
                                                    "tile_id": 2,
                                                    "tile_name": "三萬"
                                                },
                                                {
                                                    "id": 440,
                                                    "tile_id": 3,
                                                    "tile_name": "四萬"
                                                },
                                                {
                                                    "id": 575,
                                                    "tile_id": 3,
                                                    "tile_name": "四萬"
                                                },
                                                {
                                                    "id": 485,
                                                    "tile_id": 5,
                                                    "tile_name": "六萬"
                                                },
                                                {
                                                    "id": 439,
                                                    "tile_id": 7,
                                                    "tile_name": "八萬"
                                                },
                                                {
                                                    "id": 438,
                                                    "tile_id": 8,
                                                    "tile_name": "九萬"
                                                },
                                                {
                                                    "id": 455,
                                                    "tile_id": 12,
                                                    "tile_name": "四筒"
                                                },
                                                {
                                                    "id": 486,
                                                    "tile_id": 13,
                                                    "tile_name": "五筒"
                                                },
                                                {
                                                    "id": 469,
                                                    "tile_id": 17,
                                                    "tile_name": "九筒"
                                                },
                                                {
                                                    "id": 472,
                                                    "tile_id": 17,
                                                    "tile_name": "九筒"
                                                },
                                                {
                                                    "id": 454,
                                                    "tile_id": 20,
                                                    "tile_name": "三條"
                                                },
                                                {
                                                    "id": 437,
                                                    "tile_id": 21,
                                                    "tile_name": "四條"
                                                },
                                                {
                                                    "id": 456,
                                                    "tile_id": 22,
                                                    "tile_name": "五條"
                                                },
                                                {
                                                    "id": 471,
                                                    "tile_id": 25,
                                                    "tile_name": "八條"
                                                },
                                                {
                                                    "id": 488,
                                                    "tile_id": 30,
                                                    "tile_name": "北風"
                                                },
                                                {
                                                    "id": 487,
                                                    "tile_id": 31,
                                                    "tile_name": "紅中"
                                                }
                                            ],
                                            "player_id": 18
                                        },
                                        "exposed_tiles": {
                                            "id": 45,
                                            "tile_count": 1,
                                            "tiles_num": "夏",
                                            "tiles_sub": "",
                                            "tiles": [
                                                {
                                                    "id": 453,
                                                    "tile_id": 35,
                                                    "tile_name": "夏"
                                                }
                                            ],
                                            "player_id": 18
                                        },
                                        "discarded_tiles": {
                                            "id": 46,
                                            "tile_count": 0,
                                            "tiles_num": "",
                                            "tiles_sub": "",
                                            "tiles": [],
                                            "player_id": 18
                                        }
                                    },
                                    {
                                        "id": 15,
                                        "hand_id": 4,
                                        "game_player_id": 16,
                                        "hand_tiles": {
                                            "id": 47,
                                            "tile_count": 16,
                                            "tiles_num": "四八 二二三八 二三五五七八 南南北北",
                                            "tiles_sub": "萬萬 筒筒筒筒 條條條條條條 風風風風",
                                            "tiles": [
                                                {
                                                    "id": 444,
                                                    "tile_id": 3,
                                                    "tile_name": "四萬"
                                                },
                                                {
                                                    "id": 458,
                                                    "tile_id": 7,
                                                    "tile_name": "八萬"
                                                },
                                                {
                                                    "id": 441,
                                                    "tile_id": 10,
                                                    "tile_name": "二筒"
                                                },
                                                {
                                                    "id": 442,
                                                    "tile_id": 10,
                                                    "tile_name": "二筒"
                                                },
                                                {
                                                    "id": 459,
                                                    "tile_id": 11,
                                                    "tile_name": "三筒"
                                                },
                                                {
                                                    "id": 572,
                                                    "tile_id": 16,
                                                    "tile_name": "八筒"
                                                },
                                                {
                                                    "id": 489,
                                                    "tile_id": 19,
                                                    "tile_name": "二條"
                                                },
                                                {
                                                    "id": 492,
                                                    "tile_id": 20,
                                                    "tile_name": "三條"
                                                },
                                                {
                                                    "id": 443,
                                                    "tile_id": 22,
                                                    "tile_name": "五條"
                                                },
                                                {
                                                    "id": 474,
                                                    "tile_id": 22,
                                                    "tile_name": "五條"
                                                },
                                                {
                                                    "id": 476,
                                                    "tile_id": 24,
                                                    "tile_name": "七條"
                                                },
                                                {
                                                    "id": 491,
                                                    "tile_id": 25,
                                                    "tile_name": "八條"
                                                },
                                                {
                                                    "id": 460,
                                                    "tile_id": 28,
                                                    "tile_name": "南風"
                                                },
                                                {
                                                    "id": 473,
                                                    "tile_id": 28,
                                                    "tile_name": "南風"
                                                },
                                                {
                                                    "id": 475,
                                                    "tile_id": 30,
                                                    "tile_name": "北風"
                                                },
                                                {
                                                    "id": 574,
                                                    "tile_id": 30,
                                                    "tile_name": "北風"
                                                }
                                            ],
                                            "player_id": 16
                                        },
                                        "exposed_tiles": {
                                            "id": 48,
                                            "tile_count": 2,
                                            "tiles_num": "竹 春",
                                            "tiles_sub": " ",
                                            "tiles": [
                                                {
                                                    "id": 490,
                                                    "tile_id": 40,
                                                    "tile_name": "竹"
                                                },
                                                {
                                                    "id": 457,
                                                    "tile_id": 34,
                                                    "tile_name": "春"
                                                }
                                            ],
                                            "player_id": 16
                                        },
                                        "discarded_tiles": {
                                            "id": 49,
                                            "tile_count": 0,
                                            "tiles_num": "",
                                            "tiles_sub": "",
                                            "tiles": [],
                                            "player_id": 16
                                        }
                                    },
                                    {
                                        "id": 16,
                                        "hand_id": 4,
                                        "game_player_id": 15,
                                        "hand_tiles": {
                                            "id": 50,
                                            "tile_count": 16,
                                            "tiles_num": "五六七九 七 一二三四六七八 東東南西",
                                            "tiles_sub": "萬萬萬萬 筒 條條條條條條條 風風風風",
                                            "tiles": [
                                                {
                                                    "id": 495,
                                                    "tile_id": 4,
                                                    "tile_name": "五萬"
                                                },
                                                {
                                                    "id": 573,
                                                    "tile_id": 5,
                                                    "tile_name": "六萬"
                                                },
                                                {
                                                    "id": 448,
                                                    "tile_id": 6,
                                                    "tile_name": "七萬"
                                                },
                                                {
                                                    "id": 446,
                                                    "tile_id": 8,
                                                    "tile_name": "九萬"
                                                },
                                                {
                                                    "id": 447,
                                                    "tile_id": 15,
                                                    "tile_name": "七筒"
                                                },
                                                {
                                                    "id": 462,
                                                    "tile_id": 18,
                                                    "tile_name": "一條"
                                                },
                                                {
                                                    "id": 461,
                                                    "tile_id": 19,
                                                    "tile_name": "二條"
                                                },
                                                {
                                                    "id": 494,
                                                    "tile_id": 20,
                                                    "tile_name": "三條"
                                                },
                                                {
                                                    "id": 493,
                                                    "tile_id": 21,
                                                    "tile_name": "四條"
                                                },
                                                {
                                                    "id": 496,
                                                    "tile_id": 23,
                                                    "tile_name": "六條"
                                                },
                                                {
                                                    "id": 445,
                                                    "tile_id": 24,
                                                    "tile_name": "七條"
                                                },
                                                {
                                                    "id": 464,
                                                    "tile_id": 25,
                                                    "tile_name": "八條"
                                                },
                                                {
                                                    "id": 463,
                                                    "tile_id": 27,
                                                    "tile_name": "東風"
                                                },
                                                {
                                                    "id": 478,
                                                    "tile_id": 27,
                                                    "tile_name": "東風"
                                                },
                                                {
                                                    "id": 480,
                                                    "tile_id": 28,
                                                    "tile_name": "南風"
                                                },
                                                {
                                                    "id": 477,
                                                    "tile_id": 29,
                                                    "tile_name": "西風"
                                                }
                                            ],
                                            "player_id": 15
                                        },
                                        "exposed_tiles": {
                                            "id": 51,
                                            "tile_count": 1,
                                            "tiles_num": "蘭",
                                            "tiles_sub": "",
                                            "tiles": [
                                                {
                                                    "id": 479,
                                                    "tile_id": 39,
                                                    "tile_name": "蘭"
                                                }
                                            ],
                                            "player_id": 15
                                        },
                                        "discarded_tiles": {
                                            "id": 52,
                                            "tile_count": 0,
                                            "tiles_num": "",
                                            "tiles_sub": "",
                                            "tiles": [],
                                            "player_id": 15
                                        }
                                    }
                                ],
                                "wall_tiles": {
                                    "id": 40,
                                    "tile_count": 74,
                                    "tiles": [
                                        {
                                            "id": 498,
                                            "tile_id": 20,
                                            "tile_name": "三條"
                                        },
                                        {
                                            "id": 499,
                                            "tile_id": 16,
                                            "tile_name": "八筒"
                                        },
                                        {
                                            "id": 500,
                                            "tile_id": 18,
                                            "tile_name": "一條"
                                        },
                                        {
                                            "id": 501,
                                            "tile_id": 15,
                                            "tile_name": "七筒"
                                        },
                                        {
                                            "id": 502,
                                            "tile_id": 32,
                                            "tile_name": "發財"
                                        },
                                        {
                                            "id": 503,
                                            "tile_id": 19,
                                            "tile_name": "二條"
                                        },
                                        {
                                            "id": 504,
                                            "tile_id": 9,
                                            "tile_name": "一筒"
                                        },
                                        {
                                            "id": 505,
                                            "tile_id": 14,
                                            "tile_name": "六筒"
                                        },
                                        {
                                            "id": 506,
                                            "tile_id": 0,
                                            "tile_name": "一萬"
                                        },
                                        {
                                            "id": 507,
                                            "tile_id": 1,
                                            "tile_name": "二萬"
                                        },
                                        {
                                            "id": 508,
                                            "tile_id": 23,
                                            "tile_name": "六條"
                                        },
                                        {
                                            "id": 509,
                                            "tile_id": 8,
                                            "tile_name": "九萬"
                                        },
                                        {
                                            "id": 510,
                                            "tile_id": 1,
                                            "tile_name": "二萬"
                                        },
                                        {
                                            "id": 511,
                                            "tile_id": 12,
                                            "tile_name": "四筒"
                                        },
                                        {
                                            "id": 512,
                                            "tile_id": 10,
                                            "tile_name": "二筒"
                                        },
                                        {
                                            "id": 513,
                                            "tile_id": 23,
                                            "tile_name": "六條"
                                        },
                                        {
                                            "id": 514,
                                            "tile_id": 15,
                                            "tile_name": "七筒"
                                        },
                                        {
                                            "id": 515,
                                            "tile_id": 21,
                                            "tile_name": "四條"
                                        },
                                        {
                                            "id": 516,
                                            "tile_id": 16,
                                            "tile_name": "八筒"
                                        },
                                        {
                                            "id": 517,
                                            "tile_id": 32,
                                            "tile_name": "發財"
                                        },
                                        {
                                            "id": 518,
                                            "tile_id": 3,
                                            "tile_name": "四萬"
                                        },
                                        {
                                            "id": 519,
                                            "tile_id": 36,
                                            "tile_name": "秋"
                                        },
                                        {
                                            "id": 520,
                                            "tile_id": 31,
                                            "tile_name": "紅中"
                                        },
                                        {
                                            "id": 521,
                                            "tile_id": 22,
                                            "tile_name": "五條"
                                        },
                                        {
                                            "id": 522,
                                            "tile_id": 24,
                                            "tile_name": "七條"
                                        },
                                        {
                                            "id": 523,
                                            "tile_id": 32,
                                            "tile_name": "發財"
                                        },
                                        {
                                            "id": 524,
                                            "tile_id": 7,
                                            "tile_name": "八萬"
                                        },
                                        {
                                            "id": 525,
                                            "tile_id": 6,
                                            "tile_name": "七萬"
                                        },
                                        {
                                            "id": 526,
                                            "tile_id": 21,
                                            "tile_name": "四條"
                                        },
                                        {
                                            "id": 527,
                                            "tile_id": 29,
                                            "tile_name": "西風"
                                        },
                                        {
                                            "id": 528,
                                            "tile_id": 12,
                                            "tile_name": "四筒"
                                        },
                                        {
                                            "id": 529,
                                            "tile_id": 16,
                                            "tile_name": "八筒"
                                        },
                                        {
                                            "id": 530,
                                            "tile_id": 29,
                                            "tile_name": "西風"
                                        },
                                        {
                                            "id": 531,
                                            "tile_id": 2,
                                            "tile_name": "三萬"
                                        },
                                        {
                                            "id": 532,
                                            "tile_id": 31,
                                            "tile_name": "紅中"
                                        },
                                        {
                                            "id": 533,
                                            "tile_id": 30,
                                            "tile_name": "北風"
                                        },
                                        {
                                            "id": 534,
                                            "tile_id": 10,
                                            "tile_name": "二筒"
                                        },
                                        {
                                            "id": 535,
                                            "tile_id": 11,
                                            "tile_name": "三筒"
                                        },
                                        {
                                            "id": 536,
                                            "tile_id": 14,
                                            "tile_name": "六筒"
                                        },
                                        {
                                            "id": 537,
                                            "tile_id": 5,
                                            "tile_name": "六萬"
                                        },
                                        {
                                            "id": 538,
                                            "tile_id": 11,
                                            "tile_name": "三筒"
                                        },
                                        {
                                            "id": 539,
                                            "tile_id": 9,
                                            "tile_name": "一筒"
                                        },
                                        {
                                            "id": 540,
                                            "tile_id": 0,
                                            "tile_name": "一萬"
                                        },
                                        {
                                            "id": 541,
                                            "tile_id": 27,
                                            "tile_name": "東風"
                                        },
                                        {
                                            "id": 542,
                                            "tile_id": 1,
                                            "tile_name": "二萬"
                                        },
                                        {
                                            "id": 543,
                                            "tile_id": 13,
                                            "tile_name": "五筒"
                                        },
                                        {
                                            "id": 544,
                                            "tile_id": 0,
                                            "tile_name": "一萬"
                                        },
                                        {
                                            "id": 545,
                                            "tile_id": 5,
                                            "tile_name": "六萬"
                                        },
                                        {
                                            "id": 546,
                                            "tile_id": 41,
                                            "tile_name": "菊"
                                        },
                                        {
                                            "id": 547,
                                            "tile_id": 9,
                                            "tile_name": "一筒"
                                        },
                                        {
                                            "id": 548,
                                            "tile_id": 19,
                                            "tile_name": "二條"
                                        },
                                        {
                                            "id": 549,
                                            "tile_id": 11,
                                            "tile_name": "三筒"
                                        },
                                        {
                                            "id": 550,
                                            "tile_id": 9,
                                            "tile_name": "一筒"
                                        },
                                        {
                                            "id": 551,
                                            "tile_id": 13,
                                            "tile_name": "五筒"
                                        },
                                        {
                                            "id": 552,
                                            "tile_id": 26,
                                            "tile_name": "九條"
                                        },
                                        {
                                            "id": 553,
                                            "tile_id": 24,
                                            "tile_name": "七條"
                                        },
                                        {
                                            "id": 554,
                                            "tile_id": 18,
                                            "tile_name": "一條"
                                        },
                                        {
                                            "id": 555,
                                            "tile_id": 17,
                                            "tile_name": "九筒"
                                        },
                                        {
                                            "id": 556,
                                            "tile_id": 14,
                                            "tile_name": "六筒"
                                        },
                                        {
                                            "id": 557,
                                            "tile_id": 2,
                                            "tile_name": "三萬"
                                        },
                                        {
                                            "id": 558,
                                            "tile_id": 29,
                                            "tile_name": "西風"
                                        },
                                        {
                                            "id": 559,
                                            "tile_id": 37,
                                            "tile_name": "冬"
                                        },
                                        {
                                            "id": 560,
                                            "tile_id": 4,
                                            "tile_name": "五萬"
                                        },
                                        {
                                            "id": 561,
                                            "tile_id": 25,
                                            "tile_name": "八條"
                                        },
                                        {
                                            "id": 562,
                                            "tile_id": 26,
                                            "tile_name": "九條"
                                        },
                                        {
                                            "id": 563,
                                            "tile_id": 23,
                                            "tile_name": "六條"
                                        },
                                        {
                                            "id": 564,
                                            "tile_id": 4,
                                            "tile_name": "五萬"
                                        },
                                        {
                                            "id": 565,
                                            "tile_id": 27,
                                            "tile_name": "東風"
                                        },
                                        {
                                            "id": 566,
                                            "tile_id": 15,
                                            "tile_name": "七筒"
                                        },
                                        {
                                            "id": 567,
                                            "tile_id": 8,
                                            "tile_name": "九萬"
                                        },
                                        {
                                            "id": 568,
                                            "tile_id": 31,
                                            "tile_name": "紅中"
                                        },
                                        {
                                            "id": 569,
                                            "tile_id": 6,
                                            "tile_name": "七萬"
                                        },
                                        {
                                            "id": 570,
                                            "tile_id": 1,
                                            "tile_name": "二萬"
                                        },
                                        {
                                            "id": 571,
                                            "tile_id": 13,
                                            "tile_name": "五筒"
                                        }
                                    ],
                                    "hand_id": 4
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
