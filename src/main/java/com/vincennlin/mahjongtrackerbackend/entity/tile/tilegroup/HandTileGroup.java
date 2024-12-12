package com.vincennlin.mahjongtrackerbackend.entity.tile.tilegroup;

import com.vincennlin.mahjongtrackerbackend.entity.game.GamePlayer;
import com.vincennlin.mahjongtrackerbackend.entity.game.Hand;
import com.vincennlin.mahjongtrackerbackend.entity.tile.BoardTile;
import com.vincennlin.mahjongtrackerbackend.entity.tile.PlayerTile;
import com.vincennlin.mahjongtrackerbackend.payload.game.operation.GamePlayerOperation;
import com.vincennlin.mahjongtrackerbackend.payload.game.status.GamePlayerStatus;
import com.vincennlin.mahjongtrackerbackend.payload.tile.TileComparator;
import com.vincennlin.mahjongtrackerbackend.payload.tile.impl.Tile;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

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

    public GamePlayer getGamePlayer() {
        return playerTile.getGamePlayer();
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

    private boolean hasTile(Tile tile) {
        if (tile == null) return false;
        return getCountForTile(tile) > 0;
    }

    public boolean containsFlowerTile() {
        return getTiles().stream().anyMatch(BoardTile::isFlower);
    }

    public boolean canCall(GamePlayer discardGamePlayer, Tile tile) {
        GamePlayer currentPlayer = getGamePlayer();
        if (currentPlayer == discardGamePlayer) {
            return false;
        }
        if (canCallChow(discardGamePlayer, tile) || canCallPong(discardGamePlayer, tile) || canCallKong(discardGamePlayer, tile)) {
            getGamePlayer().setStatus(GamePlayerStatus.THINKING_FOR_CALL);
            return true;
        }
        return false;
    }

    public boolean canCallChow(GamePlayer discardGamePlayer, Tile tile) {
        GamePlayer currentPlayer = getGamePlayer();
        if (currentPlayer == discardGamePlayer || discardGamePlayer != getPlayerTile().getGamePlayer().getUpwindPlayer() || !tile.isSuit()) {
            return false;
        }
        return canCallDoubleSidedChow(tile) || canCallEdgeChow(tile) || canCallInsideStraightChow(tile);
    }

    public boolean canCallInsideStraightChow(Tile tile) {
        if (tile.getRank() < 2 || tile.getRank() > 8) {
            return false;
        }
        return hasTile(tile.getPreviousTile()) && hasTile(tile.getNextTile());
    }

    public boolean canCallEdgeChow(Tile tile) {
        if (tile.getRank() != 3 && tile.getRank() != 7) {
            return false;
        }
        return (tile.getRank() == 3 && hasTile(tile.getPreviousTile()) && hasTile(tile.getPreviousTile().getPreviousTile()) ||
                (tile.getRank() == 7 && hasTile(tile.getNextTile()) && hasTile(tile.getNextTile().getNextTile())));
    }

    public boolean canCallDoubleSidedChow(Tile tile) {
        return (tile.getRank() > 2 && (tile.getPreviousTile() != null  && hasTile(tile.getPreviousTile()) && hasTile(tile.getPreviousTile().getPreviousTile()) ||
                (tile.getRank() < 8 && (tile.getNextTile() != null  && hasTile(tile.getNextTile()) && hasTile(tile.getNextTile().getNextTile())))));
    }

    public boolean canCallPong(GamePlayer discardGamePlayer, Tile tile) {
        GamePlayer currentPlayer = getPlayerTile().getGamePlayer();
        if (currentPlayer == discardGamePlayer) {
            return false;
        }
        return getCountForTile(tile) >= 2;
    }

    public boolean canCallKong(GamePlayer discardGamePlayer, Tile tile) {
        GamePlayer currentPlayer = getPlayerTile().getGamePlayer();
        if (currentPlayer == discardGamePlayer) {
            return false;
        }
        return getCountForTile(tile) == 3;
    }

    public boolean canCallConcealedKong() {
        return getTiles().stream().anyMatch(boardTile -> getCountForTile(boardTile.getTile()) == 4);
    }

    public boolean canCallAddedKong() {
        return getTiles().stream().anyMatch(boardTile -> getPlayerTile().hasPongMeldForTile(boardTile.getTile()));
    }

    public boolean canCallWin() {
        return false;
    }

    public boolean canCallSelfDraw() {
        return false;
    }
}
