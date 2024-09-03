package com.vincennlin.mahjongtrackerbackend.payload.tile;

import com.vincennlin.mahjongtrackerbackend.payload.tile.type.TileType;

public interface Tile {
    String getName();
    TileType getTileType();
}
