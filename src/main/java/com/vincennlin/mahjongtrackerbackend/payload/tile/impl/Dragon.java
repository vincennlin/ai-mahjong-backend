package com.vincennlin.mahjongtrackerbackend.payload.tile.impl;

import com.vincennlin.mahjongtrackerbackend.payload.tile.HonorTile;
import com.vincennlin.mahjongtrackerbackend.payload.tile.type.TileType;

public enum Dragon implements HonorTile {

    RED_DRAGON("紅中", 5),
    GREEN_DRAGON("發財", 6),
    WHITE_DRAGON("白板", 7);

    private final String name;
    private final int order;

    Dragon(String name, int order) {
        this.name = name;
        this.order = order;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getOrder() {
        return order;
    }

    @Override
    public TileType getTileType() {
        return TileType.HONOR;
    }
}
