package com.vincennlin.aimahjongbackend.repository.game;

import com.vincennlin.aimahjongbackend.entity.tile.tilegroup.ExposedTileGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExposedTileGroupRepository extends JpaRepository<ExposedTileGroup, Long>{
}
