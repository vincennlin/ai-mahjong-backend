package com.vincennlin.aimahjongbackend.repository.game;

import com.vincennlin.aimahjongbackend.entity.tile.tilegroup.TileGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TileGroupRepository extends JpaRepository<TileGroup, Long> {
}
