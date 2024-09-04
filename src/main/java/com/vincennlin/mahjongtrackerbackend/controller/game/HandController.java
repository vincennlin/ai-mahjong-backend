package com.vincennlin.mahjongtrackerbackend.controller.game;

import com.vincennlin.mahjongtrackerbackend.payload.game.dto.HandDto;
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

    @PostMapping("/games/{game_id}/hands")
    public ResponseEntity<HandDto> startNewHand(@PathVariable(name = "game_id") Long gameId) {

        HandDto handDto = handService.startNewHand(gameId);

        return new ResponseEntity<>(handDto, HttpStatus.CREATED);
    }
}
