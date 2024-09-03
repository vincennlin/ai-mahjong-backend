package com.vincennlin.mahjongtrackerbackend.service.game;

import com.vincennlin.mahjongtrackerbackend.payload.game.page.GamePageResponse;
import com.vincennlin.mahjongtrackerbackend.payload.game.request.CreateGameRequest;
import com.vincennlin.mahjongtrackerbackend.payload.game.dto.GameDto;
import org.springframework.data.domain.Pageable;

public interface GameService {

    GamePageResponse getGames(Pageable pageable);

    GameDto createGame(CreateGameRequest request);
}
