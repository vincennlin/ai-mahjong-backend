package com.vincennlin.mahjongtrackerbackend.repository.game;

import com.vincennlin.mahjongtrackerbackend.entity.tile.tilegroup.TileGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TileGroupRepository extends JpaRepository<TileGroup, Long> {
}
