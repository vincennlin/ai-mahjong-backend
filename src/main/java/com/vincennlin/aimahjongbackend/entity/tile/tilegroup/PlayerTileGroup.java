package com.vincennlin.aimahjongbackend.entity.tile.tilegroup;

import com.vincennlin.aimahjongbackend.entity.game.GamePlayer;
import com.vincennlin.aimahjongbackend.entity.tile.BoardTile;
import com.vincennlin.aimahjongbackend.entity.tile.PlayerTile;

import java.util.List;

public interface PlayerTileGroup {

    List<BoardTile> getTiles();

    PlayerTile getPlayerTile();

    default GamePlayer getGamePlayer() {
        return getPlayerTile().getGamePlayer();
    }

    default Long getPlayerId() {
        return getPlayerTile().getGamePlayer().getId();
    }

    String[] convertTilesToString();
}
