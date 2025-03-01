package com.vincennlin.aimahjongbackend.controller.game;

import com.vincennlin.aimahjongbackend.constant.page.PageConstants;
import com.vincennlin.aimahjongbackend.controller.game.swagger.GameControllerSwagger;
import com.vincennlin.aimahjongbackend.payload.game.dto.PlayerViewDto;
import com.vincennlin.aimahjongbackend.payload.game.page.GamePageResponse;
import com.vincennlin.aimahjongbackend.payload.game.playertype.PlayerType;
import com.vincennlin.aimahjongbackend.payload.game.request.game.CreateGameRequest;
import com.vincennlin.aimahjongbackend.payload.game.dto.GameDto;
import com.vincennlin.aimahjongbackend.payload.game.request.player.CreatePlayerRequest;
import com.vincennlin.aimahjongbackend.service.game.GameService;
import com.vincennlin.aimahjongbackend.service.game.HandService;
import com.vincennlin.aimahjongbackend.service.game.PlayerService;
import com.vincennlin.aimahjongbackend.service.user.UserService;
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
public class GameController implements GameControllerSwagger {

    private final UserService userService;
    private final GameService gameService;
    private final HandService handService;
    private final PlayerService playerService;

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



    @PostMapping("/games/auto-start")
    public ResponseEntity<PlayerViewDto> autoStartGame(@Valid @RequestBody CreateGameRequest request) {

        Long gameId = gameService.createGame(request).getId();

        CreatePlayerRequest createUser2PlayerRequest = new CreatePlayerRequest(userService.getUserEntityByUserId(3L), PlayerType.HUMAN);
        Long user2GamePlayerId = playerService.createPlayer(createUser2PlayerRequest).getId();

        CreatePlayerRequest createUser3PlayerRequest = new CreatePlayerRequest(userService.getUserEntityByUserId(4L), PlayerType.HUMAN);
        Long user3GamePlayerId = playerService.createPlayer(createUser3PlayerRequest).getId();

        CreatePlayerRequest createUser4PlayerRequest = new CreatePlayerRequest(userService.getUserEntityByUserId(5L), PlayerType.HUMAN);
        Long user4GamePlayerId = playerService.createPlayer(createUser4PlayerRequest).getId();

        gameService.addPlayerToGame(gameId, user2GamePlayerId);
        gameService.addPlayerToGame(gameId, user3GamePlayerId);
        gameService.addPlayerToGame(gameId, user4GamePlayerId);

        gameService.pickSeats(gameId);

        gameService.decideEastPlayer(gameId);

        handService.startNewHand(gameId);

        handService.initializeWallTiles(gameId);

        handService.rollDice(gameId);

        handService.dealTiles(gameId);

        handService.breakWall(gameId);

        PlayerViewDto playerViewDto = handService.initialFoulHand(gameId);

        return new ResponseEntity<>(playerViewDto, HttpStatus.OK);
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

    @PutMapping("/games/{game_id}/pick-seats")
    public ResponseEntity<GameDto> pickSeats(@PathVariable(name = "game_id") Long gameId) {

        GameDto gameDto = gameService.pickSeats(gameId);

        return new ResponseEntity<>(gameDto, HttpStatus.OK);
    }

    @PutMapping("/games/{game_id}/decide-east-player")
    public ResponseEntity<GameDto> decideEastPlayer(@PathVariable(name = "game_id") Long gameId) {

        GameDto gameDto = gameService.decideEastPlayer(gameId);

        return new ResponseEntity<>(gameDto, HttpStatus.OK);
    }

    private Pageable getPageable(Integer pageNo, Integer pageSize, String sortBy, String sortDir) {
        return PageRequest.of(pageNo, pageSize,
                sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending());
    }
}
