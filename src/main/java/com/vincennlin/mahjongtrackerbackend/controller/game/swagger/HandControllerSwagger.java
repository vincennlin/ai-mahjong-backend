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
                                            "tiles_num": "冬 蘭 秋",
                                            "tiles_sub": "  ",
                                            "tiles": [
                                                {
                                                    "id": 165,
                                                    "tile_name": "冬"
                                                },
                                                {
                                                    "id": 167,
                                                    "tile_name": "蘭"
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
                                            "tiles_num": "冬 蘭 秋",
                                            "tiles_sub": "  ",
                                            "tiles": [
                                                {
                                                    "id": 165,
                                                    "tile_name": "冬"
                                                },
                                                {
                                                    "id": 167,
                                                    "tile_name": "蘭"
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
                                "hand_id": 2,
                                "round_wind": "EAST",
                                "prevailing_wind": "EAST",
                                "active_game_player_id": 10,
                                "last_discarded_tile": {
                                    "id": 148,
                                    "tile_name": "白板"
                                },
                                "status": "WAITING_FOR_DRAW",
                                "acceptable_operations": [
                                    "DRAW_TILE"
                                ],
                                "player_tiles": [
                                    {
                                        "id": 5,
                                        "hand_id": 2,
                                        "game_player_id": 10,
                                        "hand_tiles": {
                                            "id": 15,
                                            "tile_count": 16,
                                            "tiles_num": "六 一三六六七八九 三四五五五六七 北",
                                            "tiles_sub": "萬 筒筒筒筒筒筒筒 條條條條條條條 風",
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
                                            "tile_count": 1,
                                            "tiles_num": "白",
                                            "tiles_sub": "板",
                                            "tiles": [
                                                {
                                                    "id": 148,
                                                    "tile_name": "白板"
                                                }
                                            ],
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
                                            "tiles_num": "冬 蘭 秋",
                                            "tiles_sub": "  ",
                                            "tiles": [
                                                {
                                                    "id": 165,
                                                    "tile_name": "冬"
                                                },
                                                {
                                                    "id": 167,
                                                    "tile_name": "蘭"
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
    @PostMapping("/games/{game_id}/discard/{board_tile_id}")
    ResponseEntity<BoardDto> discardTile(@PathVariable(name = "game_id") Long gameId,
                                                @PathVariable(name = "board_tile_id") Long boardTileId);


    @PostMapping("/games/{game_id}/game-players/{game_player_id}/discard/{board_tile_id}")
    ResponseEntity<BoardDto> discardTileByGamePlayerId(@PathVariable(name = "game_id") Long gameId,
                                                              @PathVariable(name = "game_player_id") Long gamePlayerId,
                                                              @PathVariable(name = "board_tile_id") Long boardTileId);

    @PostMapping("/games/{game_id}/game-players/{game_player_id}/cancel-for-call")
    ResponseEntity<PlayerViewDto> cancelForCall(@PathVariable(name = "game_id") Long gameId,
                                                       @PathVariable(name = "game_player_id") Long gamePlayerId);
}
