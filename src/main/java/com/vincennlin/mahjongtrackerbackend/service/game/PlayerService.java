package com.vincennlin.mahjongtrackerbackend.service.game;

import com.vincennlin.mahjongtrackerbackend.entity.game.Player;
import com.vincennlin.mahjongtrackerbackend.payload.game.page.PlayerPageResponse;
import com.vincennlin.mahjongtrackerbackend.payload.game.request.CreatePlayerRequest;
import com.vincennlin.mahjongtrackerbackend.payload.game.dto.PlayerDto;
import org.springframework.data.domain.Pageable;

public interface PlayerService {

    PlayerPageResponse getPlayers(Pageable pageable);

    Player getPlayerEntityById(Long playerId);

    Player getCurrentPlayer();

    PlayerDto createPlayer(CreatePlayerRequest request);

    PlayerDto updatePlayer(Long playerId, PlayerDto playerDto);

    void deletePlayerById(Long playerId);
}
