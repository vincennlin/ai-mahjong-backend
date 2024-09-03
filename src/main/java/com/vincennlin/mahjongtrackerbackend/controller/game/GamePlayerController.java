package com.vincennlin.mahjongtrackerbackend.controller.game;

import com.vincennlin.mahjongtrackerbackend.payload.game.dto.GamePlayerDto;
import com.vincennlin.mahjongtrackerbackend.service.game.GamePlayerService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class GamePlayerController {

    private final GamePlayerService gamePlayerService;

    @GetMapping("/games/{game_id}/players/{player_id}/game-player")
    public ResponseEntity<GamePlayerDto> getGamePlayerByGameIdAndPlayerId(@PathVariable(name = "game_id") Long gameId,
                                                                          @PathVariable(name = "player_id") Long playerId) {

        GamePlayerDto gamePlayerDto = gamePlayerService.getGamePlayerByGameIdAndPlayerId(gameId, playerId);

        return new ResponseEntity<>(gamePlayerDto, HttpStatus.OK);
    }
}
