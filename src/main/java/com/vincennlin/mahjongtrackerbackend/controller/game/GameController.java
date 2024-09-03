package com.vincennlin.mahjongtrackerbackend.controller.game;

import com.vincennlin.mahjongtrackerbackend.constant.page.PageConstants;
import com.vincennlin.mahjongtrackerbackend.payload.game.page.GamePageResponse;
import com.vincennlin.mahjongtrackerbackend.payload.game.request.CreateGameRequest;
import com.vincennlin.mahjongtrackerbackend.payload.game.dto.GameDto;
import com.vincennlin.mahjongtrackerbackend.service.game.GameService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class GameController {

    private final GameService gameService;

    @GetMapping("/games")
    public ResponseEntity<GamePageResponse> getGames(
            @RequestParam(name = "pageNo", defaultValue = PageConstants.DEFAULT_PAGE_NUMBER, required = false) @Min(0) Integer pageNo,
            @RequestParam(name = "pageSize", defaultValue = PageConstants.DEFAULT_PAGE_SIZE, required = false) @Max(100) @Min(1) Integer pageSize,
            @RequestParam(name = "sortBy", defaultValue = PageConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(name = "sortDir", defaultValue = PageConstants.DEFAULT_SORT_DIR, required = false) String sortDir) {

        Pageable pageable = getPageable(pageNo, pageSize, sortBy, sortDir);

        GamePageResponse gamePageResponse = gameService.getGames(pageable);

        return new ResponseEntity<>(gamePageResponse, HttpStatus.OK);
    }

    @GetMapping("/games/{game_id}")
    public ResponseEntity<GameDto> getGameById(@PathVariable(name = "game_id") Long gameId) {

        GameDto gameDto = gameService.getGameById(gameId);

        return new ResponseEntity<>(gameDto, HttpStatus.OK);
    }

    @PostMapping("/games")
    public ResponseEntity<GameDto> createGame(@Valid @RequestBody CreateGameRequest request) {

        GameDto gameDto = gameService.createGame(request);

        return new ResponseEntity<>(gameDto, HttpStatus.CREATED);
    }

    @PutMapping("/games/{game_id}")
    public ResponseEntity<GameDto> updateGame(@PathVariable(name = "game_id") Long gameId,
                                              @Valid @RequestBody GameDto gameDto) {

        GameDto updatedGameDto = gameService.updateGame(gameId, gameDto);

        return new ResponseEntity<>(updatedGameDto, HttpStatus.OK);
    }

    @DeleteMapping("/games/{game_id}")
    public ResponseEntity<Void> deleteGameById(@PathVariable(name = "game_id") Long gameId) {

        gameService.deleteGameById(gameId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/games/{game_id}/players/{player_id}")
    public ResponseEntity<GameDto> addPlayerToGame(@PathVariable(name = "game_id") Long gameId,
                                                  @PathVariable(name = "player_id") Long playerId) {

        GameDto gameDto = gameService.addPlayerToGame(gameId, playerId);

        return new ResponseEntity<>(gameDto, HttpStatus.OK);
    }

    @DeleteMapping("/games/{game_id}/players/{player_id}")
    public ResponseEntity<GameDto> removePlayerFromGame(@PathVariable(name = "game_id") Long gameId,
                                                  @PathVariable(name = "player_id") Long playerId) {

        GameDto gameDto = gameService.removePlayerFromGame(gameId, playerId);

        return new ResponseEntity<>(gameDto, HttpStatus.OK);
    }

    private Pageable getPageable(Integer pageNo, Integer pageSize, String sortBy, String sortDir) {
        return PageRequest.of(pageNo, pageSize,
                sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending());
    }
}
