package com.vincennlin.mahjongtrackerbackend.repository.game;

import com.vincennlin.mahjongtrackerbackend.entity.tile.BoardTile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardTileRepository extends JpaRepository<BoardTile, Long>{
}
