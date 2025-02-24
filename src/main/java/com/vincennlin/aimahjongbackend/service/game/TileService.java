package com.vincennlin.aimahjongbackend.service.game;

import com.vincennlin.aimahjongbackend.entity.game.Hand;
import com.vincennlin.aimahjongbackend.entity.game.Operation;
import com.vincennlin.aimahjongbackend.entity.tile.BoardTile;
import com.vincennlin.aimahjongbackend.entity.tile.PlayerTile;
import com.vincennlin.aimahjongbackend.entity.tile.tilegroup.WallTileGroup;

import java.util.List;

public interface TileService {

    List<PlayerTile> createPlayerTileList(Hand hand);

    WallTileGroup createWallTileGroup(Hand hand);

    List<BoardTile> saveBoardTiles(List<BoardTile> boardTiles);

//    List<PlayerTile> savePlayerTileList(List<PlayerTile> playerTileList);

    int getFirstTileIndex(Integer diceNumber);

    List<PlayerTile> dealTiles(Hand hand);

    void sortHandGroupTiles(List<PlayerTile> playerTileList);

    void reorderWallTiles(int diceNumber, WallTileGroup wallTileGroup);

    void breakWall(PlayerTile playerTile, WallTileGroup wallTileGroup);

    int initialFoulHand(PlayerTile playerTile, WallTileGroup wallTileGroup, int foulHandCount, boolean isDealer);

    BoardTile drawTile(PlayerTile playerTile, WallTileGroup wallTileGroup);

    BoardTile foulHand(PlayerTile playerTile, WallTileGroup wallTileGroup);

    BoardTile discardTile(PlayerTile playerTile, Long boardTileId, Operation operation);

    BoardTile setLastDrawnTile(PlayerTile playerTile, BoardTile boardTile);

    void pongTile(PlayerTile playerTile, PlayerTile discardedPlayerTile, Operation operation);

    void chowTile(PlayerTile playerTile, PlayerTile discardedPlayerTile, Operation operation, int combinationIndex);

    void exposeKongTile(PlayerTile playerTile, PlayerTile discardedPlayerTile, Operation operation);

    void concealKongTile(PlayerTile playerTile, Operation operation, int combinationIndex);
}
