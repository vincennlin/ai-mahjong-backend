package com.vincennlin.mahjongtrackerbackend.controller.game;

import com.vincennlin.mahjongtrackerbackend.payload.game.dto.BoardDto;
import com.vincennlin.mahjongtrackerbackend.payload.game.dto.HandDto;
import com.vincennlin.mahjongtrackerbackend.payload.game.dto.PlayerViewDto;
import com.vincennlin.mahjongtrackerbackend.service.game.HandService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class HandController {

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
    public ResponseEntity<BoardDto> initialFoulHand(@PathVariable(name = "game_id") Long gameId) {

        BoardDto boardDto = handService.initialFoulHand(gameId);

        return new ResponseEntity<>(boardDto, HttpStatus.OK);
    }

    @PostMapping("/games/{game_id}/discard/{board_tile_id}")
    public ResponseEntity<BoardDto> discardTile(@PathVariable(name = "game_id") Long gameId,
                                                @PathVariable(name = "board_tile_id") Long boardTileId) {

        BoardDto boardDto = handService.discardTile(gameId, null, boardTileId);

        return new ResponseEntity<>(boardDto, HttpStatus.OK);
    }

    @PostMapping("/games/{game_id}/game-players/{game_player_id}/discard/{board_tile_id}")
    public ResponseEntity<BoardDto> discardTileByGamePlayerId(@PathVariable(name = "game_id") Long gameId,
                                                @PathVariable(name = "game_player_id") Long gamePlayerId,
                                                @PathVariable(name = "board_tile_id") Long boardTileId) {

        BoardDto boardDto = handService.discardTile(gameId, gamePlayerId, boardTileId);

        return new ResponseEntity<>(boardDto, HttpStatus.OK);
    }
}
