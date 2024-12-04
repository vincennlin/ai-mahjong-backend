package com.vincennlin.mahjongtrackerbackend.controller.game;

import com.vincennlin.mahjongtrackerbackend.controller.game.swagger.RoundControllerSwagger;
import com.vincennlin.mahjongtrackerbackend.payload.game.dto.RoundDto;
import com.vincennlin.mahjongtrackerbackend.service.game.RoundService;
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
public class RoundController implements RoundControllerSwagger {

    private final RoundService roundService;

    @GetMapping("games/{game_id}/rounds/current")
    public ResponseEntity<RoundDto> getCurrentRoundByGameId(@PathVariable(name = "game_id") Long gameId) {

        RoundDto roundDto = roundService.getCurrentRoundByGameId(gameId);

        return new ResponseEntity<>(roundDto, HttpStatus.OK);
    }
}
