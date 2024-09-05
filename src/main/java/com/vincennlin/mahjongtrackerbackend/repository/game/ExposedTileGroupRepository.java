package com.vincennlin.mahjongtrackerbackend.repository.game;

import com.vincennlin.mahjongtrackerbackend.entity.tile.tilegroup.ExposedTileGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExposedTileGroupRepository extends JpaRepository<ExposedTileGroup, Long>{
}
