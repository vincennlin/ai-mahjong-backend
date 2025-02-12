package com.vincennlin.aimahjongbackend.repository.game;

import com.vincennlin.aimahjongbackend.entity.tile.tilegroup.DrawnTileGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DrawnTileGroupRepository extends JpaRepository<DrawnTileGroup, Long> {
}
