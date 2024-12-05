package com.vincennlin.mahjongtrackerbackend.controller.game.swagger;

import com.vincennlin.mahjongtrackerbackend.payload.game.dto.BoardDto;
import com.vincennlin.mahjongtrackerbackend.payload.game.dto.HandDto;
import com.vincennlin.mahjongtrackerbackend.payload.game.dto.PlayerViewDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Tag(
        name = "Hand Controller",
        description = "遊戲中每手相關的 API"
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
                            {
                                "id": 1,
                                "status": "IN_PROGRESS",
                                "acceptable_operations": [
                                    "FINISH_ROUND",
                                    "FINISH_GAME"
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
                                        "id": 9,
                                        "player_id": 3,
                                        "type": "BOT",
                                        "player_name": "user_BOT2"
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
    @GetMapping("/games/{game_id}/hands/current")
    ResponseEntity<HandDto> getCurrentHandByGameId(@PathVariable(name = "game_id") Long gameId);

    @Operation(
            summary = "取得目前牌面",
            description = "取得目前牌面，在遊戲狀態 \"IN_PROGRESS\" 時可以呼叫此 API，取得目前局資訊。"
    )
    @ApiResponse(
            responseCode = "200",
            description = "成功取得目前牌面",
            content = @Content(
                    mediaType = "application/json",
                    examples = @ExampleObject(value = """
                            {
                                "game_id": 5,
                                "hand_id": 4,
                                "round_wind": "EAST",
                                "prevailing_wind": "EAST",
                                "active_game_player_id": 17,
                                "last_discarded_tile": {
                                    "id": 465,
                                    "tile_id": 33,
                                    "tile_name": "白板"
                                },
                                "status": "WAITING_FOR_DRAW",
                                "acceptable_operations": [
                                    "DRAW_TILE"
                                ],
                                "player_tiles": [
                                    {
                                        "id": 13,
                                        "hand_id": 4,
                                        "game_player_id": 17,
                                        "hand_tiles": {
                                            "id": 41,
                                            "tile_count": 16,
                                            "tiles_num": "一三五七八 四六九 一九九 南 發白白白",
                                            "tiles_sub": "萬萬萬萬萬 筒筒筒 條條條 風 財板板板",
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
                                            "tile_count": 1,
                                            "tiles_num": "白",
                                            "tiles_sub": "板",
                                            "tiles": [
                                                {
                                                    "id": 465,
                                                    "tile_id": 33,
                                                    "tile_name": "白板"
                                                }
                                            ],
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
                                            "tiles_num": "春 竹",
                                            "tiles_sub": " ",
                                            "tiles": [
                                                {
                                                    "id": 457,
                                                    "tile_id": 34,
                                                    "tile_name": "春"
                                                },
                                                {
                                                    "id": 490,
                                                    "tile_id": 40,
                                                    "tile_name": "竹"
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
    @GetMapping("/games/{game_id}/boards")
    ResponseEntity<BoardDto> getBoardByGameId(@PathVariable(name = "game_id") Long gameId);

    @Operation(
            summary = "取得目前玩家視角",
            description = "取得目前玩家視角，在遊戲狀態 \"IN_PROGRESS\" 時可以呼叫此 API，取得目前玩家視角。"
    )
    @ApiResponse(
            responseCode = "200",
            description = "成功取得目前玩家視角",
            content = @Content(
                    mediaType = "application/json",
                    examples = @ExampleObject(value = """
                            {
                                "game_id": 5,
                                "hand_id": 4,
                                "round_wind": "EAST",
                                "prevailing_wind": "EAST",
                                "active_game_player_id": 17,
                                "last_discarded_tile": {
                                    "id": 465,
                                    "tile_id": 33,
                                    "tile_name": "白板"
                                },
                                "status": "WAITING_FOR_DRAW",
                                "acceptable_operations": [
                                    "DRAW_TILE"
                                ],
                                "player_tiles": [
                                    {
                                        "id": 13,
                                        "hand_id": 4,
                                        "game_player_id": 17,
                                        "hand_tiles": {
                                            "id": 41,
                                            "tile_count": 16,
                                            "tiles_num": "一三五七八 四六九 一九九 南 發白白白",
                                            "tiles_sub": "萬萬萬萬萬 筒筒筒 條條條 風 財板板板",
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
                                            "tile_count": 1,
                                            "tiles_num": "白",
                                            "tiles_sub": "板",
                                            "tiles": [
                                                {
                                                    "id": 465,
                                                    "tile_id": 33,
                                                    "tile_name": "白板"
                                                }
                                            ],
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
                                            "tiles_num": "春 竹",
                                            "tiles_sub": " ",
                                            "tiles": [
                                                {
                                                    "id": 457,
                                                    "tile_id": 34,
                                                    "tile_name": "春"
                                                },
                                                {
                                                    "id": 490,
                                                    "tile_id": 40,
                                                    "tile_name": "竹"
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
    @GetMapping("/games/{game_id}/current-player-view")
    ResponseEntity<PlayerViewDto> getCurrentPlayerViewByGameId(@PathVariable(name = "game_id") Long gameId);

    @GetMapping("/games/{game_id}/game-players/{game_player_id}/player-view")
    ResponseEntity<PlayerViewDto> getPlayerViewByGameIdAndGamePlayerId(@PathVariable(name = "game_id") Long gameId,
                                                                              @PathVariable(name = "game_player_id") Long gamePlayerId);

    @Operation(
            summary = "開始新的一手",
            description = "開始新的一手，在遊戲狀態 \"READY_TO_START_NEW_ROUND\" 或 \"READY_TO_START_NEW_HAND\" 時可以呼叫此 API，開始後將遊戲狀態改為 \"READY_TO_INITIALIZE_WALL_TILES\" 。"
    )
    @ApiResponse(
            responseCode = "201",
            description = "成功開始新的一手",
            content = @Content(
                    mediaType = "application/json",
                    examples = @ExampleObject(value = """
                            {
                                "id": 2,
                                "status": "READY_TO_INITIALIZE_WALL_TILES",
                                "acceptable_operations": [
                                    "INITIALIZE_WALL_TILES"
                                ],
                                "round_wind": "EAST",
                                "prevailing_wind": "EAST",
                                "dealer": {
                                    "id": 10
                                },
                                "dice_number": null
                            }
                            """)
            )
    )
    @PostMapping("/games/{game_id}/hands")
    ResponseEntity<HandDto> startNewHand(@PathVariable(name = "game_id") Long gameId);

    @Operation(
            summary = "初始化牌牆",
            description = "初始化牌牆，在遊戲狀態 \"READY_TO_INITIALIZE_WALL_TILES\" 時可以呼叫此 API，初始化後將遊戲狀態改為 \"IN_PROGRESS\"，將新的一局狀態設為 \"READY_TO_ROLL_DICE\" 。"
    )
    @ApiResponse(
            responseCode = "200",
            description = "成功初始化牌牆",
            content = @Content(
                    mediaType = "application/json",
                    examples = @ExampleObject(value = """
                            {
                                "id": 2,
                                "status": "IN_PROGRESS",
                                "acceptable_operations": [
                                    "ROLL_DICE"
                                ],
                                "round_wind": "EAST",
                                "prevailing_wind": "EAST",
                                "dealer": {
                                    "id": 10
                                },
                                "dice_number": null
                            }
                            """)
            )
    )
    @PostMapping("/games/{game_id}/hands/initialize-wall-tiles")
    ResponseEntity<BoardDto> initializeWallTiles(@PathVariable(name = "game_id") Long gameId);

    @Operation(
            summary = "擲骰子",
            description = "擲骰子，在局狀態 \"READY_TO_ROLL_DICE\" 時可以呼叫此 API，擲骰子後將局狀態改為 \"READY_TO_DEAL_TILES\" 。"
    )
    @ApiResponse(
            responseCode = "200",
            description = "成功擲骰子",
            content = @Content(
                    mediaType = "application/json",
                    examples = @ExampleObject(value = """
                            {
                                "id": 2,
                                "status": "READY_TO_DEAL_TILES",
                                "acceptable_operations": [
                                    "DEAL_TILES"
                                ],
                                "round_wind": "EAST",
                                "prevailing_wind": "EAST",
                                "dealer": {
                                    "id": 10
                                },
                                "dice_number": 17
                            }
                            """)
            )
    )
    @PostMapping("/games/{game_id}/hands/roll-dice")
    ResponseEntity<HandDto> rollDice(@PathVariable(name = "game_id") Long gameId);

    @Operation(
            summary = "初始抓牌",
            description = "初始抓牌，在局狀態 \"READY_TO_DEAL_TILES\" 時可以呼叫此 API，發牌後將局狀態改為 \"FINISHED_DEALING\" 。"
    )
    @ApiResponse(
            responseCode = "200",
            description = "成功擲骰子",
            content = @Content(
                    mediaType = "application/json",
                    examples = @ExampleObject(value = """
                            {
                                "hand_id": 2,
                                "round_wind": "EAST",
                                "prevailing_wind": "EAST",
                                "active_game_player_id": 10,
                                "last_discarded_tile": null,
                                "status": "FINISHED_DEALING",
                                "acceptable_operations": [
                                    "BREAK_WALL"
                                ],
                                "player_tiles": [
                                    {
                                        "id": 5,
                                        "hand_id": 2,
                                        "game_player_id": 10,
                                        "hand_tiles": {
                                            "id": 15,
                                            "tile_count": 16,
                                            "tiles_num": "六三六 白 四 七 七三五六 北 五 一 六 八 五",
                                            "tiles_sub": "筒筒筒 板 條 筒 條條條條 風 條 筒 萬 筒 條",
                                            "tiles": [
                                                {
                                                    "id": 145,
                                                    "tile_name": "六筒"
                                                },
                                                {
                                                    "id": 146,
                                                    "tile_name": "三筒"
                                                },
                                                {
                                                    "id": 147,
                                                    "tile_name": "六筒"
                                                },
                                                {
                                                    "id": 148,
                                                    "tile_name": "白板"
                                                },
                                                {
                                                    "id": 161,
                                                    "tile_name": "四條"
                                                },
                                                {
                                                    "id": 162,
                                                    "tile_name": "七筒"
                                                },
                                                {
                                                    "id": 163,
                                                    "tile_name": "七條"
                                                },
                                                {
                                                    "id": 164,
                                                    "tile_name": "三條"
                                                },
                                                {
                                                    "id": 177,
                                                    "tile_name": "五條"
                                                },
                                                {
                                                    "id": 178,
                                                    "tile_name": "六條"
                                                },
                                                {
                                                    "id": 179,
                                                    "tile_name": "北風"
                                                },
                                                {
                                                    "id": 180,
                                                    "tile_name": "五條"
                                                },
                                                {
                                                    "id": 193,
                                                    "tile_name": "一筒"
                                                },
                                                {
                                                    "id": 194,
                                                    "tile_name": "六萬"
                                                },
                                                {
                                                    "id": 195,
                                                    "tile_name": "八筒"
                                                },
                                                {
                                                    "id": 196,
                                                    "tile_name": "五條"
                                                }
                                            ],
                                            "player_id": 10
                                        },
                                        "exposed_tiles": {
                                            "id": 16,
                                            "tile_count": 0,
                                            "tiles_num": "",
                                            "tiles_sub": "",
                                            "tiles": [],
                                            "player_id": 10
                                        },
                                        "discarded_tiles": {
                                            "id": 17,
                                            "tile_count": 0,
                                            "tiles_num": "",
                                            "tiles_sub": "",
                                            "tiles": [],
                                            "player_id": 10
                                        }
                                    },
                                    {
                                        "id": 6,
                                        "hand_id": 2,
                                        "game_player_id": 9,
                                        "hand_tiles": {
                                            "id": 18,
                                            "tile_count": 16,
                                            "tiles_num": "六 三 七二 冬 三 蘭 三 七 南 一 四 西 秋 北 二",
                                            "tiles_sub": "條 萬 條條  條  筒 萬 風 筒 萬 風  風 條",
                                            "tiles": [
                                                {
                                                    "id": 149,
                                                    "tile_name": "六條"
                                                },
                                                {
                                                    "id": 150,
                                                    "tile_name": "三萬"
                                                },
                                                {
                                                    "id": 151,
                                                    "tile_name": "七條"
                                                },
                                                {
                                                    "id": 152,
                                                    "tile_name": "二條"
                                                },
                                                {
                                                    "id": 165,
                                                    "tile_name": "冬"
                                                },
                                                {
                                                    "id": 166,
                                                    "tile_name": "三條"
                                                },
                                                {
                                                    "id": 167,
                                                    "tile_name": "蘭"
                                                },
                                                {
                                                    "id": 168,
                                                    "tile_name": "三筒"
                                                },
                                                {
                                                    "id": 181,
                                                    "tile_name": "七萬"
                                                },
                                                {
                                                    "id": 182,
                                                    "tile_name": "南風"
                                                },
                                                {
                                                    "id": 183,
                                                    "tile_name": "一筒"
                                                },
                                                {
                                                    "id": 184,
                                                    "tile_name": "四萬"
                                                },
                                                {
                                                    "id": 197,
                                                    "tile_name": "西風"
                                                },
                                                {
                                                    "id": 198,
                                                    "tile_name": "秋"
                                                },
                                                {
                                                    "id": 199,
                                                    "tile_name": "北風"
                                                },
                                                {
                                                    "id": 200,
                                                    "tile_name": "二條"
                                                }
                                            ],
                                            "player_id": 9
                                        },
                                        "exposed_tiles": {
                                            "id": 19,
                                            "tile_count": 0,
                                            "tiles_num": "",
                                            "tiles_sub": "",
                                            "tiles": [],
                                            "player_id": 9
                                        },
                                        "discarded_tiles": {
                                            "id": 20,
                                            "tile_count": 0,
                                            "tiles_num": "",
                                            "tiles_sub": "",
                                            "tiles": [],
                                            "player_id": 9
                                        }
                                    },
                                    {
                                        "id": 7,
                                        "hand_id": 2,
                                        "game_player_id": 1,
                                        "hand_tiles": {
                                            "id": 21,
                                            "tile_count": 16,
                                            "tiles_num": "二 紅 六 夏 六 九 二六 七 東 八 三二一 六 三",
                                            "tiles_sub": "筒 中 筒  萬 筒 萬萬 筒 風 條 萬萬萬 條 萬",
                                            "tiles": [
                                                {
                                                    "id": 153,
                                                    "tile_name": "二筒"
                                                },
                                                {
                                                    "id": 154,
                                                    "tile_name": "紅中"
                                                },
                                                {
                                                    "id": 155,
                                                    "tile_name": "六筒"
                                                },
                                                {
                                                    "id": 156,
                                                    "tile_name": "夏"
                                                },
                                                {
                                                    "id": 169,
                                                    "tile_name": "六萬"
                                                },
                                                {
                                                    "id": 170,
                                                    "tile_name": "九筒"
                                                },
                                                {
                                                    "id": 171,
                                                    "tile_name": "二萬"
                                                },
                                                {
                                                    "id": 172,
                                                    "tile_name": "六萬"
                                                },
                                                {
                                                    "id": 185,
                                                    "tile_name": "七筒"
                                                },
                                                {
                                                    "id": 186,
                                                    "tile_name": "東風"
                                                },
                                                {
                                                    "id": 187,
                                                    "tile_name": "八條"
                                                },
                                                {
                                                    "id": 188,
                                                    "tile_name": "三萬"
                                                },
                                                {
                                                    "id": 201,
                                                    "tile_name": "二萬"
                                                },
                                                {
                                                    "id": 202,
                                                    "tile_name": "一萬"
                                                },
                                                {
                                                    "id": 203,
                                                    "tile_name": "六條"
                                                },
                                                {
                                                    "id": 204,
                                                    "tile_name": "三萬"
                                                }
                                            ],
                                            "player_id": 1
                                        },
                                        "exposed_tiles": {
                                            "id": 22,
                                            "tile_count": 0,
                                            "tiles_num": "",
                                            "tiles_sub": "",
                                            "tiles": [],
                                            "player_id": 1
                                        },
                                        "discarded_tiles": {
                                            "id": 23,
                                            "tile_count": 0,
                                            "tiles_num": "",
                                            "tiles_sub": "",
                                            "tiles": [],
                                            "player_id": 1
                                        }
                                    },
                                    {
                                        "id": 8,
                                        "hand_id": 2,
                                        "game_player_id": 8,
                                        "hand_tiles": {
                                            "id": 24,
                                            "tile_count": 16,
                                            "tiles_num": "八 八 白 南 四 六 七 發 西 紅 四 四 四三 八 發",
                                            "tiles_sub": "萬 條 板 風 萬 條 萬 財 風 中 筒 萬 條條 萬 財",
                                            "tiles": [
                                                {
                                                    "id": 157,
                                                    "tile_name": "八萬"
                                                },
                                                {
                                                    "id": 158,
                                                    "tile_name": "八條"
                                                },
                                                {
                                                    "id": 159,
                                                    "tile_name": "白板"
                                                },
                                                {
                                                    "id": 160,
                                                    "tile_name": "南風"
                                                },
                                                {
                                                    "id": 173,
                                                    "tile_name": "四萬"
                                                },
                                                {
                                                    "id": 174,
                                                    "tile_name": "六條"
                                                },
                                                {
                                                    "id": 175,
                                                    "tile_name": "七萬"
                                                },
                                                {
                                                    "id": 176,
                                                    "tile_name": "發財"
                                                },
                                                {
                                                    "id": 189,
                                                    "tile_name": "西風"
                                                },
                                                {
                                                    "id": 190,
                                                    "tile_name": "紅中"
                                                },
                                                {
                                                    "id": 191,
                                                    "tile_name": "四筒"
                                                },
                                                {
                                                    "id": 192,
                                                    "tile_name": "四萬"
                                                },
                                                {
                                                    "id": 205,
                                                    "tile_name": "四條"
                                                },
                                                {
                                                    "id": 206,
                                                    "tile_name": "三條"
                                                },
                                                {
                                                    "id": 207,
                                                    "tile_name": "八萬"
                                                },
                                                {
                                                    "id": 208,
                                                    "tile_name": "發財"
                                                }
                                            ],
                                            "player_id": 8
                                        },
                                        "exposed_tiles": {
                                            "id": 25,
                                            "tile_count": 0,
                                            "tiles_num": "",
                                            "tiles_sub": "",
                                            "tiles": [],
                                            "player_id": 8
                                        },
                                        "discarded_tiles": {
                                            "id": 26,
                                            "tile_count": 0,
                                            "tiles_num": "",
                                            "tiles_sub": "",
                                            "tiles": [],
                                            "player_id": 8
                                        }
                                    }
                                ],
                                "wall_tiles": {
                                    "id": 14,
                                    "tile_count": 80,
                                    "tiles": [
                                        {
                                            "id": 209,
                                            "tile_name": "九筒"
                                        },
                                        {
                                            "id": 210,
                                            "tile_name": "四筒"
                                        },
                                        {
                                            "id": 211,
                                            "tile_name": "南風"
                                        },
                                        {
                                            "id": 212,
                                            "tile_name": "一萬"
                                        },
                                        {
                                            "id": 213,
                                            "tile_name": "白板"
                                        },
                                        {
                                            "id": 214,
                                            "tile_name": "三筒"
                                        },
                                        {
                                            "id": 215,
                                            "tile_name": "五筒"
                                        },
                                        {
                                            "id": 216,
                                            "tile_name": "四萬"
                                        },
                                        {
                                            "id": 217,
                                            "tile_name": "紅中"
                                        },
                                        {
                                            "id": 218,
                                            "tile_name": "九筒"
                                        },
                                        {
                                            "id": 219,
                                            "tile_name": "九條"
                                        },
                                        {
                                            "id": 220,
                                            "tile_name": "七萬"
                                        },
                                        {
                                            "id": 221,
                                            "tile_name": "五萬"
                                        },
                                        {
                                            "id": 222,
                                            "tile_name": "八萬"
                                        },
                                        {
                                            "id": 223,
                                            "tile_name": "西風"
                                        },
                                        {
                                            "id": 224,
                                            "tile_name": "北風"
                                        },
                                        {
                                            "id": 225,
                                            "tile_name": "一筒"
                                        },
                                        {
                                            "id": 226,
                                            "tile_name": "八條"
                                        },
                                        {
                                            "id": 227,
                                            "tile_name": "白板"
                                        },
                                        {
                                            "id": 228,
                                            "tile_name": "五萬"
                                        },
                                        {
                                            "id": 229,
                                            "tile_name": "八筒"
                                        },
                                        {
                                            "id": 230,
                                            "tile_name": "一條"
                                        },
                                        {
                                            "id": 231,
                                            "tile_name": "二筒"
                                        },
                                        {
                                            "id": 232,
                                            "tile_name": "五條"
                                        },
                                        {
                                            "id": 233,
                                            "tile_name": "一條"
                                        },
                                        {
                                            "id": 234,
                                            "tile_name": "一萬"
                                        },
                                        {
                                            "id": 235,
                                            "tile_name": "四條"
                                        },
                                        {
                                            "id": 236,
                                            "tile_name": "九條"
                                        },
                                        {
                                            "id": 237,
                                            "tile_name": "一條"
                                        },
                                        {
                                            "id": 238,
                                            "tile_name": "一筒"
                                        },
                                        {
                                            "id": 239,
                                            "tile_name": "五筒"
                                        },
                                        {
                                            "id": 240,
                                            "tile_name": "七萬"
                                        },
                                        {
                                            "id": 241,
                                            "tile_name": "八條"
                                        },
                                        {
                                            "id": 242,
                                            "tile_name": "八萬"
                                        },
                                        {
                                            "id": 243,
                                            "tile_name": "東風"
                                        },
                                        {
                                            "id": 244,
                                            "tile_name": "九萬"
                                        },
                                        {
                                            "id": 245,
                                            "tile_name": "五筒"
                                        },
                                        {
                                            "id": 246,
                                            "tile_name": "二萬"
                                        },
                                        {
                                            "id": 247,
                                            "tile_name": "二筒"
                                        },
                                        {
                                            "id": 248,
                                            "tile_name": "九萬"
                                        },
                                        {
                                            "id": 249,
                                            "tile_name": "東風"
                                        },
                                        {
                                            "id": 250,
                                            "tile_name": "九條"
                                        },
                                        {
                                            "id": 251,
                                            "tile_name": "四筒"
                                        },
                                        {
                                            "id": 252,
                                            "tile_name": "五筒"
                                        },
                                        {
                                            "id": 253,
                                            "tile_name": "三條"
                                        },
                                        {
                                            "id": 254,
                                            "tile_name": "九條"
                                        },
                                        {
                                            "id": 255,
                                            "tile_name": "六萬"
                                        },
                                        {
                                            "id": 256,
                                            "tile_name": "七筒"
                                        },
                                        {
                                            "id": 257,
                                            "tile_name": "八筒"
                                        },
                                        {
                                            "id": 258,
                                            "tile_name": "四條"
                                        },
                                        {
                                            "id": 259,
                                            "tile_name": "梅"
                                        },
                                        {
                                            "id": 260,
                                            "tile_name": "發財"
                                        },
                                        {
                                            "id": 261,
                                            "tile_name": "七條"
                                        },
                                        {
                                            "id": 262,
                                            "tile_name": "七筒"
                                        },
                                        {
                                            "id": 263,
                                            "tile_name": "二筒"
                                        },
                                        {
                                            "id": 264,
                                            "tile_name": "東風"
                                        },
                                        {
                                            "id": 265,
                                            "tile_name": "八筒"
                                        },
                                        {
                                            "id": 266,
                                            "tile_name": "發財"
                                        },
                                        {
                                            "id": 267,
                                            "tile_name": "南風"
                                        },
                                        {
                                            "id": 268,
                                            "tile_name": "九筒"
                                        },
                                        {
                                            "id": 269,
                                            "tile_name": "春"
                                        },
                                        {
                                            "id": 270,
                                            "tile_name": "北風"
                                        },
                                        {
                                            "id": 271,
                                            "tile_name": "二條"
                                        },
                                        {
                                            "id": 272,
                                            "tile_name": "一條"
                                        },
                                        {
                                            "id": 273,
                                            "tile_name": "菊"
                                        },
                                        {
                                            "id": 274,
                                            "tile_name": "二萬"
                                        },
                                        {
                                            "id": 275,
                                            "tile_name": "九萬"
                                        },
                                        {
                                            "id": 276,
                                            "tile_name": "五萬"
                                        },
                                        {
                                            "id": 277,
                                            "tile_name": "六筒"
                                        },
                                        {
                                            "id": 278,
                                            "tile_name": "七條"
                                        },
                                        {
                                            "id": 279,
                                            "tile_name": "一萬"
                                        },
                                        {
                                            "id": 280,
                                            "tile_name": "竹"
                                        },
                                        {
                                            "id": 281,
                                            "tile_name": "四筒"
                                        },
                                        {
                                            "id": 282,
                                            "tile_name": "九萬"
                                        },
                                        {
                                            "id": 283,
                                            "tile_name": "三萬"
                                        },
                                        {
                                            "id": 284,
                                            "tile_name": "紅中"
                                        },
                                        {
                                            "id": 285,
                                            "tile_name": "西風"
                                        },
                                        {
                                            "id": 286,
                                            "tile_name": "二條"
                                        },
                                        {
                                            "id": 287,
                                            "tile_name": "三筒"
                                        },
                                        {
                                            "id": 288,
                                            "tile_name": "五萬"
                                        }
                                    ],
                                    "hand_id": 2
                                }
                            }
                            """)
            )
    )
    @PostMapping("/games/{game_id}/hands/deal-tiles")
    ResponseEntity<BoardDto> dealTiles(@PathVariable(name = "game_id") Long gameId);

    @Operation(
            summary = "開門",
            description = "莊家開門，在局狀態 \"FINISHED_DEALING\" 時可以呼叫此 API，開門後將遊戲狀態改為 \"FINISHED_BREAKING_WALL\" 。"
    )
    @ApiResponse(
            responseCode = "200",
            description = "成功擲骰子",
            content = @Content(
                    mediaType = "application/json",
                    examples = @ExampleObject(value = """
                            {
                                "hand_id": 2,
                                "round_wind": "EAST",
                                "prevailing_wind": "EAST",
                                "active_game_player_id": 10,
                                "last_discarded_tile": null,
                                "status": "FINISHED_BREAKING_WALL",
                                "acceptable_operations": [
                                    "FOUL_HAND"
                                ],
                                "player_tiles": [
                                    {
                                        "id": 5,
                                        "hand_id": 2,
                                        "game_player_id": 10,
                                        "hand_tiles": {
                                            "id": 15,
                                            "tile_count": 17,
                                            "tiles_num": "六三六 白 四 七 七三五六 北 五 一 六 八 五 九",
                                            "tiles_sub": "筒筒筒 板 條 筒 條條條條 風 條 筒 萬 筒 條 筒",
                                            "tiles": [
                                                {
                                                    "id": 145,
                                                    "tile_name": "六筒"
                                                },
                                                {
                                                    "id": 146,
                                                    "tile_name": "三筒"
                                                },
                                                {
                                                    "id": 147,
                                                    "tile_name": "六筒"
                                                },
                                                {
                                                    "id": 148,
                                                    "tile_name": "白板"
                                                },
                                                {
                                                    "id": 161,
                                                    "tile_name": "四條"
                                                },
                                                {
                                                    "id": 162,
                                                    "tile_name": "七筒"
                                                },
                                                {
                                                    "id": 163,
                                                    "tile_name": "七條"
                                                },
                                                {
                                                    "id": 164,
                                                    "tile_name": "三條"
                                                },
                                                {
                                                    "id": 177,
                                                    "tile_name": "五條"
                                                },
                                                {
                                                    "id": 178,
                                                    "tile_name": "六條"
                                                },
                                                {
                                                    "id": 179,
                                                    "tile_name": "北風"
                                                },
                                                {
                                                    "id": 180,
                                                    "tile_name": "五條"
                                                },
                                                {
                                                    "id": 193,
                                                    "tile_name": "一筒"
                                                },
                                                {
                                                    "id": 194,
                                                    "tile_name": "六萬"
                                                },
                                                {
                                                    "id": 195,
                                                    "tile_name": "八筒"
                                                },
                                                {
                                                    "id": 196,
                                                    "tile_name": "五條"
                                                },
                                                {
                                                    "id": 209,
                                                    "tile_name": "九筒"
                                                }
                                            ],
                                            "player_id": 10
                                        },
                                        "exposed_tiles": {
                                            "id": 16,
                                            "tile_count": 0,
                                            "tiles_num": "",
                                            "tiles_sub": "",
                                            "tiles": [],
                                            "player_id": 10
                                        },
                                        "discarded_tiles": {
                                            "id": 17,
                                            "tile_count": 0,
                                            "tiles_num": "",
                                            "tiles_sub": "",
                                            "tiles": [],
                                            "player_id": 10
                                        }
                                    },
                                    {
                                        "id": 6,
                                        "hand_id": 2,
                                        "game_player_id": 9,
                                        "hand_tiles": {
                                            "id": 18,
                                            "tile_count": 16,
                                            "tiles_num": "六 三 七二 冬 三 蘭 三 七 南 一 四 西 秋 北 二",
                                            "tiles_sub": "條 萬 條條  條  筒 萬 風 筒 萬 風  風 條",
                                            "tiles": [
                                                {
                                                    "id": 149,
                                                    "tile_name": "六條"
                                                },
                                                {
                                                    "id": 150,
                                                    "tile_name": "三萬"
                                                },
                                                {
                                                    "id": 151,
                                                    "tile_name": "七條"
                                                },
                                                {
                                                    "id": 152,
                                                    "tile_name": "二條"
                                                },
                                                {
                                                    "id": 165,
                                                    "tile_name": "冬"
                                                },
                                                {
                                                    "id": 166,
                                                    "tile_name": "三條"
                                                },
                                                {
                                                    "id": 167,
                                                    "tile_name": "蘭"
                                                },
                                                {
                                                    "id": 168,
                                                    "tile_name": "三筒"
                                                },
                                                {
                                                    "id": 181,
                                                    "tile_name": "七萬"
                                                },
                                                {
                                                    "id": 182,
                                                    "tile_name": "南風"
                                                },
                                                {
                                                    "id": 183,
                                                    "tile_name": "一筒"
                                                },
                                                {
                                                    "id": 184,
                                                    "tile_name": "四萬"
                                                },
                                                {
                                                    "id": 197,
                                                    "tile_name": "西風"
                                                },
                                                {
                                                    "id": 198,
                                                    "tile_name": "秋"
                                                },
                                                {
                                                    "id": 199,
                                                    "tile_name": "北風"
                                                },
                                                {
                                                    "id": 200,
                                                    "tile_name": "二條"
                                                }
                                            ],
                                            "player_id": 9
                                        },
                                        "exposed_tiles": {
                                            "id": 19,
                                            "tile_count": 0,
                                            "tiles_num": "",
                                            "tiles_sub": "",
                                            "tiles": [],
                                            "player_id": 9
                                        },
                                        "discarded_tiles": {
                                            "id": 20,
                                            "tile_count": 0,
                                            "tiles_num": "",
                                            "tiles_sub": "",
                                            "tiles": [],
                                            "player_id": 9
                                        }
                                    },
                                    {
                                        "id": 7,
                                        "hand_id": 2,
                                        "game_player_id": 1,
                                        "hand_tiles": {
                                            "id": 21,
                                            "tile_count": 16,
                                            "tiles_num": "二 紅 六 夏 六 九 二六 七 東 八 三二一 六 三",
                                            "tiles_sub": "筒 中 筒  萬 筒 萬萬 筒 風 條 萬萬萬 條 萬",
                                            "tiles": [
                                                {
                                                    "id": 153,
                                                    "tile_name": "二筒"
                                                },
                                                {
                                                    "id": 154,
                                                    "tile_name": "紅中"
                                                },
                                                {
                                                    "id": 155,
                                                    "tile_name": "六筒"
                                                },
                                                {
                                                    "id": 156,
                                                    "tile_name": "夏"
                                                },
                                                {
                                                    "id": 169,
                                                    "tile_name": "六萬"
                                                },
                                                {
                                                    "id": 170,
                                                    "tile_name": "九筒"
                                                },
                                                {
                                                    "id": 171,
                                                    "tile_name": "二萬"
                                                },
                                                {
                                                    "id": 172,
                                                    "tile_name": "六萬"
                                                },
                                                {
                                                    "id": 185,
                                                    "tile_name": "七筒"
                                                },
                                                {
                                                    "id": 186,
                                                    "tile_name": "東風"
                                                },
                                                {
                                                    "id": 187,
                                                    "tile_name": "八條"
                                                },
                                                {
                                                    "id": 188,
                                                    "tile_name": "三萬"
                                                },
                                                {
                                                    "id": 201,
                                                    "tile_name": "二萬"
                                                },
                                                {
                                                    "id": 202,
                                                    "tile_name": "一萬"
                                                },
                                                {
                                                    "id": 203,
                                                    "tile_name": "六條"
                                                },
                                                {
                                                    "id": 204,
                                                    "tile_name": "三萬"
                                                }
                                            ],
                                            "player_id": 1
                                        },
                                        "exposed_tiles": {
                                            "id": 22,
                                            "tile_count": 0,
                                            "tiles_num": "",
                                            "tiles_sub": "",
                                            "tiles": [],
                                            "player_id": 1
                                        },
                                        "discarded_tiles": {
                                            "id": 23,
                                            "tile_count": 0,
                                            "tiles_num": "",
                                            "tiles_sub": "",
                                            "tiles": [],
                                            "player_id": 1
                                        }
                                    },
                                    {
                                        "id": 8,
                                        "hand_id": 2,
                                        "game_player_id": 8,
                                        "hand_tiles": {
                                            "id": 24,
                                            "tile_count": 16,
                                            "tiles_num": "八 八 白 南 四 六 七 發 西 紅 四 四 四三 八 發",
                                            "tiles_sub": "萬 條 板 風 萬 條 萬 財 風 中 筒 萬 條條 萬 財",
                                            "tiles": [
                                                {
                                                    "id": 157,
                                                    "tile_name": "八萬"
                                                },
                                                {
                                                    "id": 158,
                                                    "tile_name": "八條"
                                                },
                                                {
                                                    "id": 159,
                                                    "tile_name": "白板"
                                                },
                                                {
                                                    "id": 160,
                                                    "tile_name": "南風"
                                                },
                                                {
                                                    "id": 173,
                                                    "tile_name": "四萬"
                                                },
                                                {
                                                    "id": 174,
                                                    "tile_name": "六條"
                                                },
                                                {
                                                    "id": 175,
                                                    "tile_name": "七萬"
                                                },
                                                {
                                                    "id": 176,
                                                    "tile_name": "發財"
                                                },
                                                {
                                                    "id": 189,
                                                    "tile_name": "西風"
                                                },
                                                {
                                                    "id": 190,
                                                    "tile_name": "紅中"
                                                },
                                                {
                                                    "id": 191,
                                                    "tile_name": "四筒"
                                                },
                                                {
                                                    "id": 192,
                                                    "tile_name": "四萬"
                                                },
                                                {
                                                    "id": 205,
                                                    "tile_name": "四條"
                                                },
                                                {
                                                    "id": 206,
                                                    "tile_name": "三條"
                                                },
                                                {
                                                    "id": 207,
                                                    "tile_name": "八萬"
                                                },
                                                {
                                                    "id": 208,
                                                    "tile_name": "發財"
                                                }
                                            ],
                                            "player_id": 8
                                        },
                                        "exposed_tiles": {
                                            "id": 25,
                                            "tile_count": 0,
                                            "tiles_num": "",
                                            "tiles_sub": "",
                                            "tiles": [],
                                            "player_id": 8
                                        },
                                        "discarded_tiles": {
                                            "id": 26,
                                            "tile_count": 0,
                                            "tiles_num": "",
                                            "tiles_sub": "",
                                            "tiles": [],
                                            "player_id": 8
                                        }
                                    }
                                ],
                                "wall_tiles": {
                                    "id": 14,
                                    "tile_count": 79,
                                    "tiles": [
                                        {
                                            "id": 210,
                                            "tile_name": "四筒"
                                        },
                                        {
                                            "id": 211,
                                            "tile_name": "南風"
                                        },
                                        {
                                            "id": 212,
                                            "tile_name": "一萬"
                                        },
                                        {
                                            "id": 213,
                                            "tile_name": "白板"
                                        },
                                        {
                                            "id": 214,
                                            "tile_name": "三筒"
                                        },
                                        {
                                            "id": 215,
                                            "tile_name": "五筒"
                                        },
                                        {
                                            "id": 216,
                                            "tile_name": "四萬"
                                        },
                                        {
                                            "id": 217,
                                            "tile_name": "紅中"
                                        },
                                        {
                                            "id": 218,
                                            "tile_name": "九筒"
                                        },
                                        {
                                            "id": 219,
                                            "tile_name": "九條"
                                        },
                                        {
                                            "id": 220,
                                            "tile_name": "七萬"
                                        },
                                        {
                                            "id": 221,
                                            "tile_name": "五萬"
                                        },
                                        {
                                            "id": 222,
                                            "tile_name": "八萬"
                                        },
                                        {
                                            "id": 223,
                                            "tile_name": "西風"
                                        },
                                        {
                                            "id": 224,
                                            "tile_name": "北風"
                                        },
                                        {
                                            "id": 225,
                                            "tile_name": "一筒"
                                        },
                                        {
                                            "id": 226,
                                            "tile_name": "八條"
                                        },
                                        {
                                            "id": 227,
                                            "tile_name": "白板"
                                        },
                                        {
                                            "id": 228,
                                            "tile_name": "五萬"
                                        },
                                        {
                                            "id": 229,
                                            "tile_name": "八筒"
                                        },
                                        {
                                            "id": 230,
                                            "tile_name": "一條"
                                        },
                                        {
                                            "id": 231,
                                            "tile_name": "二筒"
                                        },
                                        {
                                            "id": 232,
                                            "tile_name": "五條"
                                        },
                                        {
                                            "id": 233,
                                            "tile_name": "一條"
                                        },
                                        {
                                            "id": 234,
                                            "tile_name": "一萬"
                                        },
                                        {
                                            "id": 235,
                                            "tile_name": "四條"
                                        },
                                        {
                                            "id": 236,
                                            "tile_name": "九條"
                                        },
                                        {
                                            "id": 237,
                                            "tile_name": "一條"
                                        },
                                        {
                                            "id": 238,
                                            "tile_name": "一筒"
                                        },
                                        {
                                            "id": 239,
                                            "tile_name": "五筒"
                                        },
                                        {
                                            "id": 240,
                                            "tile_name": "七萬"
                                        },
                                        {
                                            "id": 241,
                                            "tile_name": "八條"
                                        },
                                        {
                                            "id": 242,
                                            "tile_name": "八萬"
                                        },
                                        {
                                            "id": 243,
                                            "tile_name": "東風"
                                        },
                                        {
                                            "id": 244,
                                            "tile_name": "九萬"
                                        },
                                        {
                                            "id": 245,
                                            "tile_name": "五筒"
                                        },
                                        {
                                            "id": 246,
                                            "tile_name": "二萬"
                                        },
                                        {
                                            "id": 247,
                                            "tile_name": "二筒"
                                        },
                                        {
                                            "id": 248,
                                            "tile_name": "九萬"
                                        },
                                        {
                                            "id": 249,
                                            "tile_name": "東風"
                                        },
                                        {
                                            "id": 250,
                                            "tile_name": "九條"
                                        },
                                        {
                                            "id": 251,
                                            "tile_name": "四筒"
                                        },
                                        {
                                            "id": 252,
                                            "tile_name": "五筒"
                                        },
                                        {
                                            "id": 253,
                                            "tile_name": "三條"
                                        },
                                        {
                                            "id": 254,
                                            "tile_name": "九條"
                                        },
                                        {
                                            "id": 255,
                                            "tile_name": "六萬"
                                        },
                                        {
                                            "id": 256,
                                            "tile_name": "七筒"
                                        },
                                        {
                                            "id": 257,
                                            "tile_name": "八筒"
                                        },
                                        {
                                            "id": 258,
                                            "tile_name": "四條"
                                        },
                                        {
                                            "id": 259,
                                            "tile_name": "梅"
                                        },
                                        {
                                            "id": 260,
                                            "tile_name": "發財"
                                        },
                                        {
                                            "id": 261,
                                            "tile_name": "七條"
                                        },
                                        {
                                            "id": 262,
                                            "tile_name": "七筒"
                                        },
                                        {
                                            "id": 263,
                                            "tile_name": "二筒"
                                        },
                                        {
                                            "id": 264,
                                            "tile_name": "東風"
                                        },
                                        {
                                            "id": 265,
                                            "tile_name": "八筒"
                                        },
                                        {
                                            "id": 266,
                                            "tile_name": "發財"
                                        },
                                        {
                                            "id": 267,
                                            "tile_name": "南風"
                                        },
                                        {
                                            "id": 268,
                                            "tile_name": "九筒"
                                        },
                                        {
                                            "id": 269,
                                            "tile_name": "春"
                                        },
                                        {
                                            "id": 270,
                                            "tile_name": "北風"
                                        },
                                        {
                                            "id": 271,
                                            "tile_name": "二條"
                                        },
                                        {
                                            "id": 272,
                                            "tile_name": "一條"
                                        },
                                        {
                                            "id": 273,
                                            "tile_name": "菊"
                                        },
                                        {
                                            "id": 274,
                                            "tile_name": "二萬"
                                        },
                                        {
                                            "id": 275,
                                            "tile_name": "九萬"
                                        },
                                        {
                                            "id": 276,
                                            "tile_name": "五萬"
                                        },
                                        {
                                            "id": 277,
                                            "tile_name": "六筒"
                                        },
                                        {
                                            "id": 278,
                                            "tile_name": "七條"
                                        },
                                        {
                                            "id": 279,
                                            "tile_name": "一萬"
                                        },
                                        {
                                            "id": 280,
                                            "tile_name": "竹"
                                        },
                                        {
                                            "id": 281,
                                            "tile_name": "四筒"
                                        },
                                        {
                                            "id": 282,
                                            "tile_name": "九萬"
                                        },
                                        {
                                            "id": 283,
                                            "tile_name": "三萬"
                                        },
                                        {
                                            "id": 284,
                                            "tile_name": "紅中"
                                        },
                                        {
                                            "id": 285,
                                            "tile_name": "西風"
                                        },
                                        {
                                            "id": 286,
                                            "tile_name": "二條"
                                        },
                                        {
                                            "id": 287,
                                            "tile_name": "三筒"
                                        },
                                        {
                                            "id": 288,
                                            "tile_name": "五萬"
                                        }
                                    ],
                                    "hand_id": 2
                                }
                            }
                            """)
            )
    )
    @PostMapping("/games/{game_id}/hands/break-wall")
    ResponseEntity<BoardDto> breakWall(@PathVariable(name = "game_id") Long gameId);

    @Operation(
            summary = "初始補花",
            description = "莊家與玩家補花，在局狀態 \"FINISHED_BREAKING_WALL\" 時可以呼叫此 API，開局後將遊戲狀態改為 \"PLAYING\" 。"
    )
    @ApiResponse(
            responseCode = "200",
            description = "成功初始補花",
            content = @Content(
                    mediaType = "application/json",
                    examples = @ExampleObject(value = """
                            {
                                "hand_id": 2,
                                "round_wind": "EAST",
                                "prevailing_wind": "EAST",
                                "active_game_player_id": 10,
                                "last_discarded_tile": null,
                                "status": "WAITING_FOR_DISCARD",
                                "acceptable_operations": [
                                    "DISCARD_TILE"
                                ],
                                "player_tiles": [
                                    {
                                        "id": 5,
                                        "hand_id": 2,
                                        "game_player_id": 10,
                                        "hand_tiles": {
                                            "id": 15,
                                            "tile_count": 17,
                                            "tiles_num": "六 一三六六七八九 三四五五五六七 北 白",
                                            "tiles_sub": "萬 筒筒筒筒筒筒筒 條條條條條條條 風 板",
                                            "tiles": [
                                                {
                                                    "id": 194,
                                                    "tile_name": "六萬"
                                                },
                                                {
                                                    "id": 193,
                                                    "tile_name": "一筒"
                                                },
                                                {
                                                    "id": 146,
                                                    "tile_name": "三筒"
                                                },
                                                {
                                                    "id": 145,
                                                    "tile_name": "六筒"
                                                },
                                                {
                                                    "id": 147,
                                                    "tile_name": "六筒"
                                                },
                                                {
                                                    "id": 162,
                                                    "tile_name": "七筒"
                                                },
                                                {
                                                    "id": 195,
                                                    "tile_name": "八筒"
                                                },
                                                {
                                                    "id": 209,
                                                    "tile_name": "九筒"
                                                },
                                                {
                                                    "id": 164,
                                                    "tile_name": "三條"
                                                },
                                                {
                                                    "id": 161,
                                                    "tile_name": "四條"
                                                },
                                                {
                                                    "id": 177,
                                                    "tile_name": "五條"
                                                },
                                                {
                                                    "id": 180,
                                                    "tile_name": "五條"
                                                },
                                                {
                                                    "id": 196,
                                                    "tile_name": "五條"
                                                },
                                                {
                                                    "id": 178,
                                                    "tile_name": "六條"
                                                },
                                                {
                                                    "id": 163,
                                                    "tile_name": "七條"
                                                },
                                                {
                                                    "id": 179,
                                                    "tile_name": "北風"
                                                },
                                                {
                                                    "id": 148,
                                                    "tile_name": "白板"
                                                }
                                            ],
                                            "player_id": 10
                                        },
                                        "exposed_tiles": {
                                            "id": 16,
                                            "tile_count": 0,
                                            "tiles_num": "",
                                            "tiles_sub": "",
                                            "tiles": [],
                                            "player_id": 10
                                        },
                                        "discarded_tiles": {
                                            "id": 17,
                                            "tile_count": 0,
                                            "tiles_num": "",
                                            "tiles_sub": "",
                                            "tiles": [],
                                            "player_id": 10
                                        }
                                    },
                                    {
                                        "id": 6,
                                        "hand_id": 2,
                                        "game_player_id": 9,
                                        "hand_tiles": {
                                            "id": 18,
                                            "tile_count": 16,
                                            "tiles_num": "三四五七 一三 二二二三六七 南西西北",
                                            "tiles_sub": "萬萬萬萬 筒筒 條條條條條條 風風風風",
                                            "tiles": [
                                                {
                                                    "id": 150,
                                                    "tile_name": "三萬"
                                                },
                                                {
                                                    "id": 184,
                                                    "tile_name": "四萬"
                                                },
                                                {
                                                    "id": 288,
                                                    "tile_name": "五萬"
                                                },
                                                {
                                                    "id": 181,
                                                    "tile_name": "七萬"
                                                },
                                                {
                                                    "id": 183,
                                                    "tile_name": "一筒"
                                                },
                                                {
                                                    "id": 168,
                                                    "tile_name": "三筒"
                                                },
                                                {
                                                    "id": 152,
                                                    "tile_name": "二條"
                                                },
                                                {
                                                    "id": 200,
                                                    "tile_name": "二條"
                                                },
                                                {
                                                    "id": 286,
                                                    "tile_name": "二條"
                                                },
                                                {
                                                    "id": 166,
                                                    "tile_name": "三條"
                                                },
                                                {
                                                    "id": 149,
                                                    "tile_name": "六條"
                                                },
                                                {
                                                    "id": 151,
                                                    "tile_name": "七條"
                                                },
                                                {
                                                    "id": 182,
                                                    "tile_name": "南風"
                                                },
                                                {
                                                    "id": 197,
                                                    "tile_name": "西風"
                                                },
                                                {
                                                    "id": 285,
                                                    "tile_name": "西風"
                                                },
                                                {
                                                    "id": 199,
                                                    "tile_name": "北風"
                                                }
                                            ],
                                            "player_id": 9
                                        },
                                        "exposed_tiles": {
                                            "id": 19,
                                            "tile_count": 3,
                                            "tiles_num": "蘭 冬秋",
                                            "tiles_sub": " ",
                                            "tiles": [
                                                {
                                                    "id": 167,
                                                    "tile_name": "蘭"
                                                },
                                                {
                                                    "id": 165,
                                                    "tile_name": "冬"
                                                },
                                                {
                                                    "id": 198,
                                                    "tile_name": "秋"
                                                }
                                            ],
                                            "player_id": 9
                                        },
                                        "discarded_tiles": {
                                            "id": 20,
                                            "tile_count": 0,
                                            "tiles_num": "",
                                            "tiles_sub": "",
                                            "tiles": [],
                                            "player_id": 9
                                        }
                                    },
                                    {
                                        "id": 7,
                                        "hand_id": 2,
                                        "game_player_id": 1,
                                        "hand_tiles": {
                                            "id": 21,
                                            "tile_count": 16,
                                            "tiles_num": "一二二三三六六 二三六七九 六八 東 紅",
                                            "tiles_sub": "萬萬萬萬萬萬萬 筒筒筒筒筒 條條 風 中",
                                            "tiles": [
                                                {
                                                    "id": 202,
                                                    "tile_name": "一萬"
                                                },
                                                {
                                                    "id": 171,
                                                    "tile_name": "二萬"
                                                },
                                                {
                                                    "id": 201,
                                                    "tile_name": "二萬"
                                                },
                                                {
                                                    "id": 188,
                                                    "tile_name": "三萬"
                                                },
                                                {
                                                    "id": 204,
                                                    "tile_name": "三萬"
                                                },
                                                {
                                                    "id": 169,
                                                    "tile_name": "六萬"
                                                },
                                                {
                                                    "id": 172,
                                                    "tile_name": "六萬"
                                                },
                                                {
                                                    "id": 153,
                                                    "tile_name": "二筒"
                                                },
                                                {
                                                    "id": 287,
                                                    "tile_name": "三筒"
                                                },
                                                {
                                                    "id": 155,
                                                    "tile_name": "六筒"
                                                },
                                                {
                                                    "id": 185,
                                                    "tile_name": "七筒"
                                                },
                                                {
                                                    "id": 170,
                                                    "tile_name": "九筒"
                                                },
                                                {
                                                    "id": 203,
                                                    "tile_name": "六條"
                                                },
                                                {
                                                    "id": 187,
                                                    "tile_name": "八條"
                                                },
                                                {
                                                    "id": 186,
                                                    "tile_name": "東風"
                                                },
                                                {
                                                    "id": 154,
                                                    "tile_name": "紅中"
                                                }
                                            ],
                                            "player_id": 1
                                        },
                                        "exposed_tiles": {
                                            "id": 22,
                                            "tile_count": 1,
                                            "tiles_num": "夏",
                                            "tiles_sub": "",
                                            "tiles": [
                                                {
                                                    "id": 156,
                                                    "tile_name": "夏"
                                                }
                                            ],
                                            "player_id": 1
                                        },
                                        "discarded_tiles": {
                                            "id": 23,
                                            "tile_count": 0,
                                            "tiles_num": "",
                                            "tiles_sub": "",
                                            "tiles": [],
                                            "player_id": 1
                                        }
                                    },
                                    {
                                        "id": 8,
                                        "hand_id": 2,
                                        "game_player_id": 8,
                                        "hand_tiles": {
                                            "id": 24,
                                            "tile_count": 16,
                                            "tiles_num": "四四七八八 四 三四六八 南西 紅發發白",
                                            "tiles_sub": "萬萬萬萬萬 筒 條條條條 風風 中財財板",
                                            "tiles": [
                                                {
                                                    "id": 173,
                                                    "tile_name": "四萬"
                                                },
                                                {
                                                    "id": 192,
                                                    "tile_name": "四萬"
                                                },
                                                {
                                                    "id": 175,
                                                    "tile_name": "七萬"
                                                },
                                                {
                                                    "id": 157,
                                                    "tile_name": "八萬"
                                                },
                                                {
                                                    "id": 207,
                                                    "tile_name": "八萬"
                                                },
                                                {
                                                    "id": 191,
                                                    "tile_name": "四筒"
                                                },
                                                {
                                                    "id": 206,
                                                    "tile_name": "三條"
                                                },
                                                {
                                                    "id": 205,
                                                    "tile_name": "四條"
                                                },
                                                {
                                                    "id": 174,
                                                    "tile_name": "六條"
                                                },
                                                {
                                                    "id": 158,
                                                    "tile_name": "八條"
                                                },
                                                {
                                                    "id": 160,
                                                    "tile_name": "南風"
                                                },
                                                {
                                                    "id": 189,
                                                    "tile_name": "西風"
                                                },
                                                {
                                                    "id": 190,
                                                    "tile_name": "紅中"
                                                },
                                                {
                                                    "id": 176,
                                                    "tile_name": "發財"
                                                },
                                                {
                                                    "id": 208,
                                                    "tile_name": "發財"
                                                },
                                                {
                                                    "id": 159,
                                                    "tile_name": "白板"
                                                }
                                            ],
                                            "player_id": 8
                                        },
                                        "exposed_tiles": {
                                            "id": 25,
                                            "tile_count": 0,
                                            "tiles_num": "",
                                            "tiles_sub": "",
                                            "tiles": [],
                                            "player_id": 8
                                        },
                                        "discarded_tiles": {
                                            "id": 26,
                                            "tile_count": 0,
                                            "tiles_num": "",
                                            "tiles_sub": "",
                                            "tiles": [],
                                            "player_id": 8
                                        }
                                    }
                                ],
                                "wall_tiles": {
                                    "id": 14,
                                    "tile_count": 75,
                                    "tiles": [
                                        {
                                            "id": 210,
                                            "tile_name": "四筒"
                                        },
                                        {
                                            "id": 211,
                                            "tile_name": "南風"
                                        },
                                        {
                                            "id": 212,
                                            "tile_name": "一萬"
                                        },
                                        {
                                            "id": 213,
                                            "tile_name": "白板"
                                        },
                                        {
                                            "id": 214,
                                            "tile_name": "三筒"
                                        },
                                        {
                                            "id": 215,
                                            "tile_name": "五筒"
                                        },
                                        {
                                            "id": 216,
                                            "tile_name": "四萬"
                                        },
                                        {
                                            "id": 217,
                                            "tile_name": "紅中"
                                        },
                                        {
                                            "id": 218,
                                            "tile_name": "九筒"
                                        },
                                        {
                                            "id": 219,
                                            "tile_name": "九條"
                                        },
                                        {
                                            "id": 220,
                                            "tile_name": "七萬"
                                        },
                                        {
                                            "id": 221,
                                            "tile_name": "五萬"
                                        },
                                        {
                                            "id": 222,
                                            "tile_name": "八萬"
                                        },
                                        {
                                            "id": 223,
                                            "tile_name": "西風"
                                        },
                                        {
                                            "id": 224,
                                            "tile_name": "北風"
                                        },
                                        {
                                            "id": 225,
                                            "tile_name": "一筒"
                                        },
                                        {
                                            "id": 226,
                                            "tile_name": "八條"
                                        },
                                        {
                                            "id": 227,
                                            "tile_name": "白板"
                                        },
                                        {
                                            "id": 228,
                                            "tile_name": "五萬"
                                        },
                                        {
                                            "id": 229,
                                            "tile_name": "八筒"
                                        },
                                        {
                                            "id": 230,
                                            "tile_name": "一條"
                                        },
                                        {
                                            "id": 231,
                                            "tile_name": "二筒"
                                        },
                                        {
                                            "id": 232,
                                            "tile_name": "五條"
                                        },
                                        {
                                            "id": 233,
                                            "tile_name": "一條"
                                        },
                                        {
                                            "id": 234,
                                            "tile_name": "一萬"
                                        },
                                        {
                                            "id": 235,
                                            "tile_name": "四條"
                                        },
                                        {
                                            "id": 236,
                                            "tile_name": "九條"
                                        },
                                        {
                                            "id": 237,
                                            "tile_name": "一條"
                                        },
                                        {
                                            "id": 238,
                                            "tile_name": "一筒"
                                        },
                                        {
                                            "id": 239,
                                            "tile_name": "五筒"
                                        },
                                        {
                                            "id": 240,
                                            "tile_name": "七萬"
                                        },
                                        {
                                            "id": 241,
                                            "tile_name": "八條"
                                        },
                                        {
                                            "id": 242,
                                            "tile_name": "八萬"
                                        },
                                        {
                                            "id": 243,
                                            "tile_name": "東風"
                                        },
                                        {
                                            "id": 244,
                                            "tile_name": "九萬"
                                        },
                                        {
                                            "id": 245,
                                            "tile_name": "五筒"
                                        },
                                        {
                                            "id": 246,
                                            "tile_name": "二萬"
                                        },
                                        {
                                            "id": 247,
                                            "tile_name": "二筒"
                                        },
                                        {
                                            "id": 248,
                                            "tile_name": "九萬"
                                        },
                                        {
                                            "id": 249,
                                            "tile_name": "東風"
                                        },
                                        {
                                            "id": 250,
                                            "tile_name": "九條"
                                        },
                                        {
                                            "id": 251,
                                            "tile_name": "四筒"
                                        },
                                        {
                                            "id": 252,
                                            "tile_name": "五筒"
                                        },
                                        {
                                            "id": 253,
                                            "tile_name": "三條"
                                        },
                                        {
                                            "id": 254,
                                            "tile_name": "九條"
                                        },
                                        {
                                            "id": 255,
                                            "tile_name": "六萬"
                                        },
                                        {
                                            "id": 256,
                                            "tile_name": "七筒"
                                        },
                                        {
                                            "id": 257,
                                            "tile_name": "八筒"
                                        },
                                        {
                                            "id": 258,
                                            "tile_name": "四條"
                                        },
                                        {
                                            "id": 259,
                                            "tile_name": "梅"
                                        },
                                        {
                                            "id": 260,
                                            "tile_name": "發財"
                                        },
                                        {
                                            "id": 261,
                                            "tile_name": "七條"
                                        },
                                        {
                                            "id": 262,
                                            "tile_name": "七筒"
                                        },
                                        {
                                            "id": 263,
                                            "tile_name": "二筒"
                                        },
                                        {
                                            "id": 264,
                                            "tile_name": "東風"
                                        },
                                        {
                                            "id": 265,
                                            "tile_name": "八筒"
                                        },
                                        {
                                            "id": 266,
                                            "tile_name": "發財"
                                        },
                                        {
                                            "id": 267,
                                            "tile_name": "南風"
                                        },
                                        {
                                            "id": 268,
                                            "tile_name": "九筒"
                                        },
                                        {
                                            "id": 269,
                                            "tile_name": "春"
                                        },
                                        {
                                            "id": 270,
                                            "tile_name": "北風"
                                        },
                                        {
                                            "id": 271,
                                            "tile_name": "二條"
                                        },
                                        {
                                            "id": 272,
                                            "tile_name": "一條"
                                        },
                                        {
                                            "id": 273,
                                            "tile_name": "菊"
                                        },
                                        {
                                            "id": 274,
                                            "tile_name": "二萬"
                                        },
                                        {
                                            "id": 275,
                                            "tile_name": "九萬"
                                        },
                                        {
                                            "id": 276,
                                            "tile_name": "五萬"
                                        },
                                        {
                                            "id": 277,
                                            "tile_name": "六筒"
                                        },
                                        {
                                            "id": 278,
                                            "tile_name": "七條"
                                        },
                                        {
                                            "id": 279,
                                            "tile_name": "一萬"
                                        },
                                        {
                                            "id": 280,
                                            "tile_name": "竹"
                                        },
                                        {
                                            "id": 281,
                                            "tile_name": "四筒"
                                        },
                                        {
                                            "id": 282,
                                            "tile_name": "九萬"
                                        },
                                        {
                                            "id": 283,
                                            "tile_name": "三萬"
                                        },
                                        {
                                            "id": 284,
                                            "tile_name": "紅中"
                                        }
                                    ],
                                    "hand_id": 2
                                }
                            }
                            """)
            )
    )
    @PostMapping("/games/{game_id}/hands/initial-foul-hand")
    ResponseEntity<BoardDto> initialFoulHand(@PathVariable(name = "game_id") Long gameId);

    @Operation(
            summary = "出牌",
            description = "玩家出牌，以牌 id 將牌從手牌中移除，加入到棄牌區。"
    )
    @ApiResponse(
            responseCode = "200",
            description = "成功出牌",
            content = @Content(
                    mediaType = "application/json",
                    examples = @ExampleObject(value = """
                            {
                                "game_id": 5,
                                "hand_id": 4,
                                "round_wind": "EAST",
                                "prevailing_wind": "EAST",
                                "active_game_player_id": 17,
                                "last_discarded_tile": {
                                    "id": 465,
                                    "tile_id": 33,
                                    "tile_name": "白板"
                                },
                                "status": "WAITING_FOR_DRAW",
                                "acceptable_operations": [
                                    "DRAW_TILE"
                                ],
                                "player_tiles": [
                                    {
                                        "id": 13,
                                        "hand_id": 4,
                                        "game_player_id": 17,
                                        "hand_tiles": {
                                            "id": 41,
                                            "tile_count": 16,
                                            "tiles_num": "一三五七八 四六九 一九九 南 發白白白",
                                            "tiles_sub": "萬萬萬萬萬 筒筒筒 條條條 風 財板板板",
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
                                            "tile_count": 1,
                                            "tiles_num": "白",
                                            "tiles_sub": "板",
                                            "tiles": [
                                                {
                                                    "id": 465,
                                                    "tile_id": 33,
                                                    "tile_name": "白板"
                                                }
                                            ],
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
                                            "tiles_num": "春 竹",
                                            "tiles_sub": " ",
                                            "tiles": [
                                                {
                                                    "id": 457,
                                                    "tile_id": 34,
                                                    "tile_name": "春"
                                                },
                                                {
                                                    "id": 490,
                                                    "tile_id": 40,
                                                    "tile_name": "竹"
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
    @PostMapping("/games/{game_id}/discard/{board_tile_id}")
    ResponseEntity<BoardDto> discardTile(@PathVariable(name = "game_id") Long gameId,
                                                @PathVariable(name = "board_tile_id") Long boardTileId);


    @Operation(
            summary = "以指定的玩家 id 的身份捨牌",
            description = "玩家出牌，以牌 id 將牌從手牌中移除，加入到棄牌區。"
    )
    @ApiResponse(
            responseCode = "200",
            description = "成功出牌",
            content = @Content(
                    mediaType = "application/json",
                    examples = @ExampleObject(value = """
                            {
                                "game_id": 5,
                                "hand_id": 4,
                                "round_wind": "EAST",
                                "prevailing_wind": "EAST",
                                "active_game_player_id": 17,
                                "last_discarded_tile": {
                                    "id": 465,
                                    "tile_id": 33,
                                    "tile_name": "白板"
                                },
                                "status": "WAITING_FOR_DRAW",
                                "acceptable_operations": [
                                    "DRAW_TILE"
                                ],
                                "player_tiles": [
                                    {
                                        "id": 13,
                                        "hand_id": 4,
                                        "game_player_id": 17,
                                        "hand_tiles": {
                                            "id": 41,
                                            "tile_count": 16,
                                            "tiles_num": "一三五七八 四六九 一九九 南 發白白白",
                                            "tiles_sub": "萬萬萬萬萬 筒筒筒 條條條 風 財板板板",
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
                                            "tile_count": 1,
                                            "tiles_num": "白",
                                            "tiles_sub": "板",
                                            "tiles": [
                                                {
                                                    "id": 465,
                                                    "tile_id": 33,
                                                    "tile_name": "白板"
                                                }
                                            ],
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
                                            "tiles_num": "春 竹",
                                            "tiles_sub": " ",
                                            "tiles": [
                                                {
                                                    "id": 457,
                                                    "tile_id": 34,
                                                    "tile_name": "春"
                                                },
                                                {
                                                    "id": 490,
                                                    "tile_id": 40,
                                                    "tile_name": "竹"
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
    @PostMapping("/games/{game_id}/game-players/{game_player_id}/discard/{board_tile_id}")
    ResponseEntity<BoardDto> discardTileByGamePlayerId(@PathVariable(name = "game_id") Long gameId,
                                                              @PathVariable(name = "game_player_id") Long gamePlayerId,
                                                              @PathVariable(name = "board_tile_id") Long boardTileId);

    @PostMapping("/games/{game_id}/game-players/{game_player_id}/cancel-for-call")
    ResponseEntity<PlayerViewDto> cancelForCall(@PathVariable(name = "game_id") Long gameId,
                                                       @PathVariable(name = "game_player_id") Long gamePlayerId);
}
