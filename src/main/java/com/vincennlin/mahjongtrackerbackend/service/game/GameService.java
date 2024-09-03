package com.vincennlin.mahjongtrackerbackend.service.game;

import com.vincennlin.mahjongtrackerbackend.entity.game.Game;
import com.vincennlin.mahjongtrackerbackend.payload.game.page.GamePageResponse;
import com.vincennlin.mahjongtrackerbackend.payload.game.request.CreateGameRequest;
import com.vincennlin.mahjongtrackerbackend.payload.game.dto.GameDto;
import org.springframework.data.domain.Pageable;

public interface GameService {

    GamePageResponse getGames(Pageable pageable);

    GameDto getGameById(Long gameId);

    Game getGameEntityById(Long gameId);

    GameDto createGame(CreateGameRequest request);

    GameDto updateGame(Long gameId, GameDto gameDto);

    void deleteGameById(Long gameId);

    GameDto addPlayerToGame(Long gameId, Long playerId);

    GameDto removePlayerFromGame(Long gameId, Long playerId);

    GameDto pickSeats(Long gameId);

    GameDto decideEastPlayer(Long gameId);
}
