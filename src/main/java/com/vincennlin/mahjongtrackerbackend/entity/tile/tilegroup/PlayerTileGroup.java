package com.vincennlin.mahjongtrackerbackend.entity.tile.tilegroup;

import com.vincennlin.mahjongtrackerbackend.entity.tile.BoardTile;
import com.vincennlin.mahjongtrackerbackend.entity.tile.PlayerTile;

import java.util.List;

public interface PlayerTileGroup {

    List<BoardTile> getTiles();

    PlayerTile getPlayerTile();

    default Long getPlayerId() {
        return getPlayerTile().getGamePlayer().getId();
    }

    String[] convertTilesToString();
}
