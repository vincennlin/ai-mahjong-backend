package com.vincennlin.aimahjongbackend.repository.game;

import com.vincennlin.aimahjongbackend.entity.tile.tilegroup.WallTileGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WallTileGroupRepository extends JpaRepository<WallTileGroup, Long>{
}
