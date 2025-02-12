package com.vincennlin.aimahjongbackend.repository.game;

import com.vincennlin.aimahjongbackend.entity.tile.tilegroup.DiscardedTileGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiscardedTileGroupRepository extends JpaRepository<DiscardedTileGroup, Long>{
}
