package com.vincennlin.mahjongtrackerbackend.entity.tile.tilegroup;

import com.vincennlin.mahjongtrackerbackend.entity.tile.BoardTile;

import java.util.List;

public interface PlayerTileGroup {

//    Boolean containsTile(Tile tile);

    List<BoardTile> getTiles();

    Long getPlayerId();
}
