package com.vincennlin.mahjongtrackerbackend.repository.game;

import com.vincennlin.mahjongtrackerbackend.entity.tile.PlayerTile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerTileRepository extends JpaRepository<PlayerTile, Long> {
}
