package com.vincennlin.mahjongtrackerbackend.payload.tile.impl;

import com.vincennlin.mahjongtrackerbackend.payload.tile.TileInterface;
import com.vincennlin.mahjongtrackerbackend.payload.tile.type.TileType;
import lombok.Getter;

@Getter
public enum Tile implements TileInterface, Comparable<Tile> {

    CHARACTER_1("一萬", 1, TileType.SUIT),
    CHARACTER_2("二萬", 2, TileType.SUIT),
    CHARACTER_3("三萬", 3, TileType.SUIT),
    CHARACTER_4("四萬", 4, TileType.SUIT),
    CHARACTER_5("五萬", 5, TileType.SUIT),
    CHARACTER_6("六萬", 6, TileType.SUIT),
    CHARACTER_7("七萬", 7, TileType.SUIT),
    CHARACTER_8("八萬", 8, TileType.SUIT),
    CHARACTER_9("九萬", 9, TileType.SUIT),

    DOT_1("一筒", 1, TileType.SUIT),
    DOT_2("二筒", 2, TileType.SUIT),
    DOT_3("三筒", 3, TileType.SUIT),
    DOT_4("四筒", 4, TileType.SUIT),
    DOT_5("五筒", 5, TileType.SUIT),
    DOT_6("六筒", 6, TileType.SUIT),
    DOT_7("七筒", 7, TileType.SUIT),
    DOT_8("八筒", 8, TileType.SUIT),
    DOT_9("九筒", 9, TileType.SUIT),

    STICK_1("一條", 1, TileType.SUIT),
    STICK_2("二條", 2, TileType.SUIT),
    STICK_3("三條", 3, TileType.SUIT),
    STICK_4("四條", 4, TileType.SUIT),
    STICK_5("五條", 5, TileType.SUIT),
    STICK_6("六條", 6, TileType.SUIT),
    STICK_7("七條", 7, TileType.SUIT),
    STICK_8("八條", 8, TileType.SUIT),
    STICK_9("九條", 9, TileType.SUIT),

    WIND_EAST("東風", 1, TileType.HONOR),
    WIND_SOUTH("南風", 2, TileType.HONOR),
    WIND_WEST("西風", 3, TileType.HONOR),
    WIND_NORTH("北風", 4, TileType.HONOR),
    DRAGON_RED("紅中", 1, TileType.HONOR),
    DRAGON_GREEN("青發", 2, TileType.HONOR),
    DRAGON_WHITE("白板", 3, TileType.HONOR),

    SEASON_SPRING("春", 1, TileType.FLOWER),
    SEASON_SUMMER("夏", 2, TileType.FLOWER),
    SEASON_AUTUMN("秋", 3, TileType.FLOWER),
    SEASON_WINTER("冬", 4, TileType.FLOWER),
    FLOWER_PLUM("梅", 1, TileType.FLOWER),
    FLOWER_ORCHID("蘭", 2, TileType.FLOWER),
    FLOWER_CHRYSANTHEMUM("菊", 3, TileType.FLOWER),
    FLOWER_BAMBOO("竹", 4, TileType.FLOWER);

    private final String name;
    private final int rank;
    private final TileType tileType;

    Tile(String name, int rank, TileType tileType) {
        this.name = name;
        this.rank = rank;
        this.tileType = tileType;
    }

    public boolean isFlower() {
        return this.tileType == TileType.FLOWER;
    }
}
