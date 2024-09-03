package com.vincennlin.mahjongtrackerbackend.payload.tile.impl;

import com.vincennlin.mahjongtrackerbackend.payload.tile.SuitTile;
import com.vincennlin.mahjongtrackerbackend.payload.tile.type.TileType;

public enum Character implements SuitTile {

    ONE("一萬", 1),
    TWO("二萬", 2),
    THREE("三萬", 3),
    FOUR("四萬", 4),
    FIVE("五萬", 5),
    SIX("六萬", 6),
    SEVEN("七萬", 7),
    EIGHT("八萬", 8),
    NINE("九萬", 9);

    private final String name;
    private final int rank;

    Character(String name, int value) {
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
