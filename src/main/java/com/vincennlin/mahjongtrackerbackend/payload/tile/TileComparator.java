package com.vincennlin.mahjongtrackerbackend.payload.tile;

import com.vincennlin.mahjongtrackerbackend.entity.tile.BoardTile;

import java.util.Comparator;

public class TileComparator implements Comparator<BoardTile> {

    @Override
    public int compare(BoardTile bt1, BoardTile bt2) {
        return Integer.compare(bt1.getTile().ordinal(), bt2.getTile().ordinal());
    }
}
