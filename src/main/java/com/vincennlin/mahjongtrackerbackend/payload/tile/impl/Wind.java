package com.vincennlin.mahjongtrackerbackend.payload.tile.impl;

import com.vincennlin.mahjongtrackerbackend.payload.tile.HonorTile;
import com.vincennlin.mahjongtrackerbackend.payload.tile.type.TileType;

public enum Wind implements HonorTile {

    EAST_WIND("東風", 1),
    SOUTH_WIND("南風", 2),
    WEST_WIND("西風", 3),
    NORTH_WIND("北風", 4);

    private final String name;
    private final int order;

    Wind(String name, int order) {
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
