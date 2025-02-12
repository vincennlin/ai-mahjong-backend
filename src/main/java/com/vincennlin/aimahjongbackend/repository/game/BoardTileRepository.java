package com.vincennlin.aimahjongbackend.repository.game;

import com.vincennlin.aimahjongbackend.entity.tile.BoardTile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardTileRepository extends JpaRepository<BoardTile, Long>{
}
