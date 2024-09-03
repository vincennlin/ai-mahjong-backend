package com.vincennlin.mahjongtrackerbackend.controller.game;

import com.vincennlin.mahjongtrackerbackend.payload.game.dto.HandDto;
import com.vincennlin.mahjongtrackerbackend.service.game.HandService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class HandController {

    private final HandService handService;

    @PostMapping("/games/{game_id}/hands")
    public ResponseEntity<HandDto> startNewHand(@PathVariable(name = "game_id") Long gameId) {

        HandDto handDto = handService.startNewHand(gameId);

        return new ResponseEntity<>(handDto, HttpStatus.CREATED);
    }
}
