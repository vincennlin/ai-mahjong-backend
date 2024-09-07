package com.vincennlin.mahjongtrackerbackend.payload.tile.impl;

import com.vincennlin.mahjongtrackerbackend.payload.tile.TileInterface;
import com.vincennlin.mahjongtrackerbackend.payload.tile.type.SubTileType;
import com.vincennlin.mahjongtrackerbackend.payload.tile.type.TileType;
import lombok.Getter;

@Getter
public enum Tile implements TileInterface, Comparable<Tile> {

    CHARACTER_1("一萬", 1, SubTileType.CHARACTER),
    CHARACTER_2("二萬", 2, SubTileType.CHARACTER),
    CHARACTER_3("三萬", 3, SubTileType.CHARACTER),
    CHARACTER_4("四萬", 4, SubTileType.CHARACTER),
    CHARACTER_5("五萬", 5, SubTileType.CHARACTER),
    CHARACTER_6("六萬", 6, SubTileType.CHARACTER),
    CHARACTER_7("七萬", 7, SubTileType.CHARACTER),
    CHARACTER_8("八萬", 8, SubTileType.CHARACTER),
    CHARACTER_9("九萬", 9, SubTileType.CHARACTER),

    DOT_1("一筒", 1, SubTileType.DOT),
    DOT_2("二筒", 2, SubTileType.DOT),
    DOT_3("三筒", 3, SubTileType.DOT),
    DOT_4("四筒", 4, SubTileType.DOT),
    DOT_5("五筒", 5, SubTileType.DOT),
    DOT_6("六筒", 6, SubTileType.DOT),
    DOT_7("七筒", 7, SubTileType.DOT),
    DOT_8("八筒", 8, SubTileType.DOT),
    DOT_9("九筒", 9, SubTileType.DOT),

    STICK_1("一條", 1, SubTileType.STICK),
    STICK_2("二條", 2, SubTileType.STICK),
    STICK_3("三條", 3, SubTileType.STICK),
    STICK_4("四條", 4, SubTileType.STICK),
    STICK_5("五條", 5, SubTileType.STICK),
    STICK_6("六條", 6, SubTileType.STICK),
    STICK_7("七條", 7, SubTileType.STICK),
    STICK_8("八條", 8, SubTileType.STICK),
    STICK_9("九條", 9, SubTileType.STICK),

    WIND_EAST("東風", 1, SubTileType.WIND),
    WIND_SOUTH("南風", 2, SubTileType.WIND),
    WIND_WEST("西風", 3, SubTileType.WIND),
    WIND_NORTH("北風", 4, SubTileType.WIND),

    DRAGON_RED("紅中", 1, SubTileType.DRAGON),
    DRAGON_GREEN("發財", 2, SubTileType.DRAGON),
    DRAGON_WHITE("白板", 3, SubTileType.DRAGON),

    SEASON_SPRING("春", 1, SubTileType.SEASON),
    SEASON_SUMMER("夏", 2, SubTileType.SEASON),
    SEASON_AUTUMN("秋", 3, SubTileType.SEASON),
    SEASON_WINTER("冬", 4, SubTileType.SEASON),
    FLOWER_PLUM("梅", 1, SubTileType.FLOWER),
    FLOWER_ORCHID("蘭", 2, SubTileType.FLOWER),
    FLOWER_BAMBOO("竹", 3, SubTileType.FLOWER),
    FLOWER_CHRYSANTHEMUM("菊", 4, SubTileType.FLOWER);

    private final String name;
    private final int rank;
    private final SubTileType subTileType;

    Tile(String name, int rank, SubTileType subTileType) {
        this.name = name;
        this.rank = rank;
        this.subTileType = subTileType;
    }

    public TileType getTileType() {
        return this.subTileType.getTileType();
    }

    public boolean isFlower() {
        return this.getTileType() == TileType.FLOWER;
    }
}
