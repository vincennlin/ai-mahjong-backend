package com.vincennlin.mahjongtrackerbackend.entity.tile.tilegroup;

import com.vincennlin.mahjongtrackerbackend.entity.game.GamePlayer;
import com.vincennlin.mahjongtrackerbackend.entity.tile.BoardTile;
import com.vincennlin.mahjongtrackerbackend.entity.tile.PlayerTile;
import com.vincennlin.mahjongtrackerbackend.payload.tile.TileComparator;
import com.vincennlin.mahjongtrackerbackend.payload.tile.impl.Tile;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "hand_tile_groups")
public class HandTileGroup extends TileGroup implements PlayerTileGroup {

    public HandTileGroup(PlayerTile playerTile) {
        super();
        this.playerTile = playerTile;
    }

    @OneToOne(
            mappedBy = "handTiles",
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL
    )
    @JoinColumn(name = "player_tile_id", referencedColumnName = "id")
    private PlayerTile playerTile;

    @Override
    public Long getPlayerId() {
        return playerTile.getGamePlayer().getId();
    }

    public void sortHandTiles() {
        getTiles().sort(new TileComparator());
    }

    public int getCountForTile(Tile tile) {
        if (tile == null) {
            return 0;
        }
        return (int) getTiles().stream().filter(boardTile -> boardTile.getTile().equals(tile)).count();
    }

    public boolean containsFlowerTile() {
        return getTiles().stream().anyMatch(BoardTile::isFlower);
    }

    public boolean canCall(GamePlayer discardGamePlayer, Tile tile) {
        return canCallChow(discardGamePlayer, tile) || canCallPong(tile) || canCallKong(tile);
    }

    public boolean canCallChow(GamePlayer discardGamePlayer, Tile tile) {
        if (discardGamePlayer != getPlayerTile().getGamePlayer().getUpwindPlayer() || !tile.isSuit()) {
            return false;
        }
        return getCountForTile(tile.getPreviousTile()) >= 1 && getCountForTile(tile.getNextTile()) >= 1;
    }

    public boolean canCallPong(Tile tile) {
        return getCountForTile(tile) >= 2;
    }

    public boolean canCallKong(Tile tile) {
        return getCountForTile(tile) >= 3;
    }

    public boolean canConcealedKong() {
        return getTiles().stream().anyMatch(boardTile -> getCountForTile(boardTile.getTile()) == 4);
    }
}
