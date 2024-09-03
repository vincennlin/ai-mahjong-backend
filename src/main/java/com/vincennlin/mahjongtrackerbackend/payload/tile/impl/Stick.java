package com.vincennlin.mahjongtrackerbackend.payload.tile.impl;

import com.vincennlin.mahjongtrackerbackend.payload.tile.SuitTile;
import com.vincennlin.mahjongtrackerbackend.payload.tile.type.TileType;

public enum Stick implements SuitTile {

    ONE("一條", 1),
    TWO("二條", 2),
    THREE("三條", 3),
    FOUR("四條", 4),
    FIVE("五條", 5),
    SIX("六條", 6),
    SEVEN("七條", 7),
    EIGHT("八條", 8),
    NINE("九條", 9);

    private final String name;
    private final int rank;

    Stick(String name, int rank) {
        this.name = name;
        this.rank = rank;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getRank() {
        return rank;
    }

    @Override
    public TileType getTileType() {
        return TileType.SUIT;
    }
}
