package com.vincennlin.aimahjongbackend.payload.tile;

import com.vincennlin.aimahjongbackend.payload.tile.type.TileType;

public interface TileInterface {

    String getName();

    int getRank();

    TileType getTileType();
}
