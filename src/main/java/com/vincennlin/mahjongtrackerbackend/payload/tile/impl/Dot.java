package com.vincennlin.mahjongtrackerbackend.payload.tile.impl;

import com.vincennlin.mahjongtrackerbackend.payload.tile.SuitTile;
import com.vincennlin.mahjongtrackerbackend.payload.tile.type.TileType;

public enum Dot implements SuitTile {

    ONE("一筒", 1),
    TWO("二筒", 2),
    THREE("三筒", 3),
    FOUR("四筒", 4),
    FIVE("五筒", 5),
    SIX("六筒", 6),
    SEVEN("七筒", 7),
    EIGHT("八筒", 8),
    NINE("九筒", 9);

    private final String name;
    private final int rank;

    Dot(String name, int value) {
        this.name = name;
        this.rank = value;
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
