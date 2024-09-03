package com.vincennlin.mahjongtrackerbackend.payload.tile.impl;

import com.vincennlin.mahjongtrackerbackend.payload.tile.FlowerTile;
import com.vincennlin.mahjongtrackerbackend.payload.tile.type.TileType;

public enum Season implements FlowerTile {

    SPRING("春", 1),
    SUMMER("夏", 2),
    AUTUMN("秋", 3),
    WINTER("冬", 4);

    private final String name;
    private final int order;

    Season(String name, int value) {
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
