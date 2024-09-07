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
    private Tile previousTile;
    private Tile nextTile;

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

    public boolean isSuit() {
        return this.getTileType() == TileType.SUIT;
    }

    static {
        CHARACTER_1.nextTile = CHARACTER_2;
        CHARACTER_2.previousTile = CHARACTER_1;
        CHARACTER_2.nextTile = CHARACTER_3;
        CHARACTER_3.previousTile = CHARACTER_2;
        CHARACTER_3.nextTile = CHARACTER_4;
        CHARACTER_4.previousTile = CHARACTER_3;
        CHARACTER_4.nextTile = CHARACTER_5;
        CHARACTER_5.previousTile = CHARACTER_4;
        CHARACTER_5.nextTile = CHARACTER_6;
        CHARACTER_6.previousTile = CHARACTER_5;
        CHARACTER_6.nextTile = CHARACTER_7;
        CHARACTER_7.previousTile = CHARACTER_6;
        CHARACTER_7.nextTile = CHARACTER_8;
        CHARACTER_8.previousTile = CHARACTER_7;
        CHARACTER_8.nextTile = CHARACTER_9;
        CHARACTER_9.previousTile = CHARACTER_8;

        DOT_1.nextTile = DOT_2;
        DOT_2.previousTile = DOT_1;
        DOT_2.nextTile = DOT_3;
        DOT_3.previousTile = DOT_2;
        DOT_3.nextTile = DOT_4;
        DOT_4.previousTile = DOT_3;
        DOT_4.nextTile = DOT_5;
        DOT_5.previousTile = DOT_4;
        DOT_5.nextTile = DOT_6;
        DOT_6.previousTile = DOT_5;
        DOT_6.nextTile = DOT_7;
        DOT_7.previousTile = DOT_6;
        DOT_7.nextTile = DOT_8;
        DOT_8.previousTile = DOT_7;
        DOT_8.nextTile = DOT_9;
        DOT_9.previousTile = DOT_8;

        STICK_1.nextTile = STICK_2;
        STICK_2.previousTile = STICK_1;
        STICK_2.nextTile = STICK_3;
        STICK_3.previousTile = STICK_2;
        STICK_3.nextTile = STICK_4;
        STICK_4.previousTile = STICK_3;
        STICK_4.nextTile = STICK_5;
        STICK_5.previousTile = STICK_4;
        STICK_5.nextTile = STICK_6;
        STICK_6.previousTile = STICK_5;
        STICK_6.nextTile = STICK_7;
        STICK_7.previousTile = STICK_6;
        STICK_7.nextTile = STICK_8;
        STICK_8.previousTile = STICK_7;
        STICK_8.nextTile = STICK_9;
        STICK_9.previousTile = STICK_8;
    }
}

