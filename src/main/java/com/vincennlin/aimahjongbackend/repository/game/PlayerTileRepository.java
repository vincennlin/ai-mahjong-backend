package com.vincennlin.aimahjongbackend.repository.game;

import com.vincennlin.aimahjongbackend.entity.tile.PlayerTile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlayerTileRepository extends JpaRepository<PlayerTile, Long> {

    Optional<PlayerTile> findByHandIdAndGamePlayerId(Long handId, Long gamePlayerId);
}
