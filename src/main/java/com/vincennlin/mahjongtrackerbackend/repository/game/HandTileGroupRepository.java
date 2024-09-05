package com.vincennlin.mahjongtrackerbackend.repository.game;

import com.vincennlin.mahjongtrackerbackend.entity.tile.tilegroup.HandTileGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HandTileGroupRepository extends JpaRepository<HandTileGroup, Long> {
}
