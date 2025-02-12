package com.vincennlin.aimahjongbackend.repository.game;

import com.vincennlin.aimahjongbackend.entity.tile.tilegroup.HandTileGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HandTileGroupRepository extends JpaRepository<HandTileGroup, Long> {
}
