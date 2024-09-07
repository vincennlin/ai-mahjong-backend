package com.vincennlin.mahjongtrackerbackend.payload.tile.type;

import com.vincennlin.mahjongtrackerbackend.payload.tile.TileInterface;
import com.vincennlin.mahjongtrackerbackend.payload.tile.impl.Tile;
import lombok.Getter;

import java.util.List;
import java.util.function.Supplier;

@Getter
public enum TileType {

    SUIT(() -> List.of(
            Tile.CHARACTER_1, Tile.CHARACTER_2, Tile.CHARACTER_3, Tile.CHARACTER_4, Tile.CHARACTER_5, Tile.CHARACTER_6, Tile.CHARACTER_7, Tile.CHARACTER_8, Tile.CHARACTER_9,
            Tile.DOT_1, Tile.DOT_2, Tile.DOT_3, Tile.DOT_4, Tile.DOT_5, Tile.DOT_6, Tile.DOT_7, Tile.DOT_8, Tile.DOT_9,
            Tile.STICK_1, Tile.STICK_2, Tile.STICK_3, Tile.STICK_4, Tile.STICK_5, Tile.STICK_6, Tile.STICK_7, Tile.STICK_8, Tile.STICK_9)
            , 4
    ),

    HONOR(() -> List.of(
            Tile.WIND_EAST, Tile.WIND_SOUTH, Tile.WIND_WEST, Tile.WIND_NORTH,
            Tile.DRAGON_RED, Tile.DRAGON_GREEN, Tile.DRAGON_WHITE)
            , 4
    ),

    FLOWER(() -> List.of(
            Tile.SEASON_SPRING, Tile.SEASON_SUMMER, Tile.SEASON_AUTUMN, Tile.SEASON_WINTER,
            Tile.FLOWER_PLUM, Tile.FLOWER_ORCHID, Tile.FLOWER_CHRYSANTHEMUM, Tile.FLOWER_BAMBOO)
            , 1
    );

    private final Supplier<List<TileInterface>> tilesSupplier;
    private final int quantity;

    TileType(Supplier<List<TileInterface>> tilesSupplier, int quantity) {
        this.tilesSupplier = tilesSupplier;
        this.quantity = quantity;
    }
}

