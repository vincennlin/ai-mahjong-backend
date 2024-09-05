package com.vincennlin.mahjongtrackerbackend.repository.game;

import com.vincennlin.mahjongtrackerbackend.entity.tile.tilegroup.WallTileGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WallTileGroupRepository extends JpaRepository<WallTileGroup, Long>{
}
