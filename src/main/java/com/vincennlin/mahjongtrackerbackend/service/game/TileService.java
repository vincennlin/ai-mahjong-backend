package com.vincennlin.mahjongtrackerbackend.service.game;

import com.vincennlin.mahjongtrackerbackend.entity.game.Hand;
import com.vincennlin.mahjongtrackerbackend.entity.tile.BoardTile;
import com.vincennlin.mahjongtrackerbackend.entity.tile.PlayerTile;
import com.vincennlin.mahjongtrackerbackend.entity.tile.tilegroup.WallTileGroup;

import java.util.List;

public interface TileService {

    WallTileGroup createWallTileGroup(Hand hand);

    List<BoardTile> saveBoardTiles(List<BoardTile> boardTiles);

    List<PlayerTile> savePlayerTileList(List<PlayerTile> playerTileList);

    List<PlayerTile> dealTiles(Hand hand);

    void sortHandGroupTiles(List<PlayerTile> playerTileList);

    void initialFoulHand(PlayerTile playerTile, WallTileGroup wallTileGroup);

    boolean drawTile(PlayerTile playerTile, WallTileGroup wallTileGroup, boolean isFromHead);
}
