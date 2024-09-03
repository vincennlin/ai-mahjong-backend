package com.vincennlin.mahjongtrackerbackend.payload.tile.impl;

import com.vincennlin.mahjongtrackerbackend.payload.tile.FlowerTile;
import com.vincennlin.mahjongtrackerbackend.payload.tile.type.TileType;

public enum Flower implements FlowerTile {

    PLUM("梅", 1),
    ORCHID("蘭", 2),
    BAMBOO("竹", 3),
    CHRYSANTHEMUM("菊", 4);

    private final String name;
    private final int order;

    Flower(String name, int value) {
        this.name = name;
        this.order = value;
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
        return TileType.FLOWER;
    }
}
