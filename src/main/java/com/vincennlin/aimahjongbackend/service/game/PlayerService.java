package com.vincennlin.aimahjongbackend.service.game;

import com.vincennlin.aimahjongbackend.entity.game.Player;
import com.vincennlin.aimahjongbackend.payload.game.page.PlayerPageResponse;
import com.vincennlin.aimahjongbackend.payload.game.request.player.CreatePlayerRequest;
import com.vincennlin.aimahjongbackend.payload.game.dto.PlayerDto;
import org.springframework.data.domain.Pageable;

public interface PlayerService {

    PlayerPageResponse getPlayers(Pageable pageable);

    Player getPlayerEntityById(Long playerId);

    Player getCurrentPlayer();

    PlayerDto createPlayer(CreatePlayerRequest request);

    PlayerDto updatePlayer(Long playerId, PlayerDto playerDto);

    void deletePlayerById(Long playerId);
}
