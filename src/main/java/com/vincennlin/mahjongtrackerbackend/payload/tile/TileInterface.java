package com.vincennlin.mahjongtrackerbackend.payload.tile;

import com.vincennlin.mahjongtrackerbackend.payload.tile.type.TileType;

public interface TileInterface {

    String getName();

    int getRank();

    TileType getTileType();
}
