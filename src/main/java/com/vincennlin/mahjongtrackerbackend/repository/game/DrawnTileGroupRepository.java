package com.vincennlin.mahjongtrackerbackend.repository.game;

import com.vincennlin.mahjongtrackerbackend.entity.tile.tilegroup.DrawnTileGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DrawnTileGroupRepository extends JpaRepository<DrawnTileGroup, Long> {
}
