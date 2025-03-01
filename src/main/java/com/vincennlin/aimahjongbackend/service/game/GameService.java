package com.vincennlin.aimahjongbackend.service.game;

import com.vincennlin.aimahjongbackend.entity.game.Game;
import com.vincennlin.aimahjongbackend.payload.game.page.GamePageResponse;
import com.vincennlin.aimahjongbackend.payload.game.request.game.CreateGameRequest;
import com.vincennlin.aimahjongbackend.payload.game.dto.GameDto;
import org.springframework.data.domain.Pageable;

public interface GameService {

    GamePageResponse getGames(Pageable pageable);

    GameDto getGameById(Long gameId);

    Game getGameEntityById(Long gameId);

    GameDto createGame(CreateGameRequest request);

    GameDto updateGame(Long gameId, GameDto gameDto);

    GameDto saveGame(Game game);

    void deleteGameById(Long gameId);

    GameDto addPlayerToGame(Long gameId, Long playerId);

    GameDto removePlayerFromGame(Long gameId, Long playerId);

    GameDto pickSeats(Long gameId);

    GameDto decideEastPlayer(Long gameId);
}
