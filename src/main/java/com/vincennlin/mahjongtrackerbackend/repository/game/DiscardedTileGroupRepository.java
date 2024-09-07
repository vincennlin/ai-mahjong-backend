package com.vincennlin.mahjongtrackerbackend.repository.game;

import com.vincennlin.mahjongtrackerbackend.entity.tile.tilegroup.DiscardedTileGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiscardedTileGroupRepository extends JpaRepository<DiscardedTileGroup, Long>{
}
