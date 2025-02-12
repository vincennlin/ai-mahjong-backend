package com.vincennlin.aimahjongbackend.controller.game;

import com.vincennlin.aimahjongbackend.controller.game.swagger.HandControllerSwagger;
import com.vincennlin.aimahjongbackend.payload.game.dto.BoardDto;
import com.vincennlin.aimahjongbackend.payload.game.dto.HandDto;
import com.vincennlin.aimahjongbackend.payload.game.dto.PlayerViewDto;
import com.vincennlin.aimahjongbackend.payload.game.request.ai.DiscardAdviceResponse;
import com.vincennlin.aimahjongbackend.service.game.HandService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class HandController implements HandControllerSwagger {

    private final HandService handService;

    @GetMapping("/games/{game_id}/hands/current")
    public ResponseEntity<HandDto> getCurrentHandByGameId(@PathVariable(name = "game_id") Long gameId) {

        HandDto handDto = handService.getCurrentHandByGameId(gameId);

        return new ResponseEntity<>(handDto, HttpStatus.OK);
    }

    @GetMapping("/games/{game_id}/boards")
    public ResponseEntity<BoardDto> getBoardByGameId(@PathVariable(name = "game_id") Long gameId) {

        BoardDto boardDto = handService.getBoardByGameId(gameId);

        return new ResponseEntity<>(boardDto, HttpStatus.OK);
    }

    @GetMapping("/games/{game_id}/current-player-view")
    public ResponseEntity<PlayerViewDto> getCurrentPlayerViewByGameId(@PathVariable(name = "game_id") Long gameId) {

        PlayerViewDto playerViewDto = handService.getCurrentPlayerViewByGameId(gameId);

        return new ResponseEntity<>(playerViewDto, HttpStatus.OK);
    }

    @GetMapping("/games/{game_id}/game-players/{game_player_id}/player-view")
    public ResponseEntity<PlayerViewDto> getPlayerViewByGameIdAndGamePlayerId(@PathVariable(name = "game_id") Long gameId,
                                                                             @PathVariable(name = "game_player_id") Long gamePlayerId) {

        PlayerViewDto playerViewDto = handService.getPlayerViewByGameIdAndGamePlayerId(gameId, gamePlayerId);

        return new ResponseEntity<>(playerViewDto, HttpStatus.OK);
    }

    @PostMapping("/games/{game_id}/hands")
    public ResponseEntity<HandDto> startNewHand(@PathVariable(name = "game_id") Long gameId) {

        HandDto handDto = handService.startNewHand(gameId);

        return new ResponseEntity<>(handDto, HttpStatus.CREATED);
    }

    @PostMapping("/games/{game_id}/hands/initialize-wall-tiles")
    public ResponseEntity<BoardDto> initializeWallTiles(@PathVariable(name = "game_id") Long gameId) {

        BoardDto boardDto = handService.initializeWallTiles(gameId);

        return new ResponseEntity<>(boardDto, HttpStatus.OK);
    }

    @PostMapping("/games/{game_id}/hands/roll-dice")
    public ResponseEntity<HandDto> rollDice(@PathVariable(name = "game_id") Long gameId) {

        HandDto handDto = handService.rollDice(gameId);

        return new ResponseEntity<>(handDto, HttpStatus.OK);
    }

    @PostMapping("/games/{game_id}/hands/deal-tiles")
    public ResponseEntity<BoardDto> dealTiles(@PathVariable(name = "game_id") Long gameId) {

        BoardDto boardDto = handService.dealTiles(gameId);

        return new ResponseEntity<>(boardDto, HttpStatus.OK);
    }

    @PostMapping("/games/{game_id}/hands/break-wall")
    public ResponseEntity<BoardDto> breakWall(@PathVariable(name = "game_id") Long gameId) {

        BoardDto boardDto = handService.breakWall(gameId);

        return new ResponseEntity<>(boardDto, HttpStatus.OK);
    }

    @PostMapping("/games/{game_id}/hands/initial-foul-hand")
    public ResponseEntity<PlayerViewDto> initialFoulHand(@PathVariable(name = "game_id") Long gameId) {

        PlayerViewDto playerViewDto = handService.initialFoulHand(gameId);

        return new ResponseEntity<>(playerViewDto, HttpStatus.OK);
    }

    @PostMapping("/games/{game_id}/discard/{board_tile_id}")
    public ResponseEntity<PlayerViewDto> discardTile(@PathVariable(name = "game_id") Long gameId,
                                                @PathVariable(name = "board_tile_id") Long boardTileId) {

        PlayerViewDto playerViewDto = handService.discardTile(gameId, boardTileId);

        return new ResponseEntity<>(playerViewDto, HttpStatus.OK);
    }

//    @PostMapping("/games/{game_id}/game-players/{game_player_id}/discard/{board_tile_id}")
//    public ResponseEntity<PlayerViewDto> discardTileByGamePlayerId(@PathVariable(name = "game_id") Long gameId,
//                                                @PathVariable(name = "game_player_id") Long gamePlayerId,
//                                                @PathVariable(name = "board_tile_id") Long boardTileId) {
//
//        PlayerViewDto playerViewDto = handService.discardTile(gameId, gamePlayerId, boardTileId);
//
//        return new ResponseEntity<>(playerViewDto, HttpStatus.OK);
//    }

    @PostMapping("/games/{game_id}/cancel-for-call")
    public ResponseEntity<PlayerViewDto> cancelForCall(@PathVariable(name = "game_id") Long gameId) {

        PlayerViewDto playerViewDto = handService.cancelForCall(gameId);

        return new ResponseEntity<>(playerViewDto, HttpStatus.OK);
    }

    @PostMapping("/games/{game_id}/draw-tile")
    public ResponseEntity<PlayerViewDto> drawTile(@PathVariable(name = "game_id") Long gameId) {

        PlayerViewDto playerViewDto = handService.drawTile(gameId);

        return new ResponseEntity<>(playerViewDto, HttpStatus.OK);
    }

    @PostMapping("/games/{game_id}/generate-discard-advice")
    public ResponseEntity<DiscardAdviceResponse> generateDiscardAdvice(@PathVariable(name = "game_id") Long gameId) {

        DiscardAdviceResponse response = handService.generateDiscardAdvice(gameId);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

//    @PostMapping("/games/{game_id}/game-players/{game_player_id}/draw-tile")
//    public ResponseEntity<PlayerViewDto> drawTileByGamePlayerId(@PathVariable(name = "game_id") Long gameId,
//                                                @PathVariable(name = "game_player_id") Long gamePlayerId) {
//
//        PlayerViewDto playerViewDto = handService.drawTile(gameId, gamePlayerId);
//
//        return new ResponseEntity<>(playerViewDto, HttpStatus.OK);
//    }

    @PostMapping("/games/{game_id}/pong-tile")
    public ResponseEntity<PlayerViewDto> pongTile(@PathVariable(name = "game_id") Long gameId) {

        PlayerViewDto playerViewDto = handService.pongTile(gameId);

        return new ResponseEntity<>(playerViewDto, HttpStatus.OK);
    }

    @PostMapping("/games/{game_id}/chow-tile/{combination_index}")
    public ResponseEntity<PlayerViewDto> chowTile(@PathVariable(name = "game_id") Long gameId,
                                                  @PathVariable(name = "combination_index") int combinationIndex) {

        PlayerViewDto playerViewDto = handService.chowTile(gameId, combinationIndex);

        return new ResponseEntity<>(playerViewDto, HttpStatus.OK);
    }

    @PostMapping("/games/{game_id}/expose-kong-tile")
    public ResponseEntity<PlayerViewDto> exposeKongTile(@PathVariable(name = "game_id") Long gameId) {

        PlayerViewDto playerViewDto = handService.exposeKongTile(gameId);

        return new ResponseEntity<>(playerViewDto, HttpStatus.OK);
    }
}
