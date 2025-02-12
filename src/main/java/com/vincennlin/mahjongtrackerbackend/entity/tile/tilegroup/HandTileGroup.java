package com.vincennlin.mahjongtrackerbackend.entity.tile.tilegroup;

import com.vincennlin.mahjongtrackerbackend.entity.game.GamePlayer;
import com.vincennlin.mahjongtrackerbackend.entity.tile.PlayerTile;
import com.vincennlin.mahjongtrackerbackend.payload.game.status.GamePlayerStatus;
import com.vincennlin.mahjongtrackerbackend.payload.tile.TileComparator;
import com.vincennlin.mahjongtrackerbackend.payload.tile.impl.Tile;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
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

    public int getCountForTile(Tile tile) {
        return getTileCountMap().getOrDefault(tile, 0);
    }

    public void sortHandTiles() {
        getTiles().sort(new TileComparator());
    }

    private boolean hasTile(Tile tile) {
        if (tile == null) return false;
        return getCountForTile(tile) > 0;
    }

    public boolean containsFlowerTile() {
        return getTileCountMap().entrySet().stream().anyMatch(entry -> entry.getKey().isFlower() && entry.getValue() > 0);
    }

    public boolean canCall(GamePlayer discardGamePlayer, Tile tile) {
        GamePlayer currentPlayer = getGamePlayer();
        if (currentPlayer == discardGamePlayer) {
            return false;
        }
        if (canCallChow(discardGamePlayer, tile) || canCallPong(discardGamePlayer, tile) || canCallExposedKong(discardGamePlayer, tile)) {
            getGamePlayer().setStatus(GamePlayerStatus.THINKING_FOR_CALL);
            return true;
        }
        return false;
    }

    public boolean canCallChow(GamePlayer discardGamePlayer, Tile tile) {
        GamePlayer currentPlayer = getGamePlayer();
        if (currentPlayer == discardGamePlayer || discardGamePlayer != currentPlayer.getUpwindPlayer() || !tile.isSuit()) {
            return false;
        }
        return canCallChow(tile);
    }

    private boolean canCallChow(Tile tile) {
        return canCallDoubleSidedChow(tile) || canCallEdgeChow(tile) || canCallInsideStraightChow(tile);
    }

    public boolean canCallPong(GamePlayer discardGamePlayer, Tile tile) {
        GamePlayer currentPlayer = getPlayerTile().getGamePlayer();
        if (currentPlayer == discardGamePlayer) {
            return false;
        }
        return getCountForTile(tile) >= 2;
    }

    public boolean canCallExposedKong(GamePlayer discardGamePlayer, Tile tile) {
        GamePlayer currentPlayer = getPlayerTile().getGamePlayer();
        if (currentPlayer == discardGamePlayer || discardGamePlayer == currentPlayer.getUpwindPlayer()) {
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

    public List<List<Tile>> getChowCombinations(Tile tile) {
        if (!canCallChow(tile)) {
            return null;
        }

        List<List<Tile>> chowCombinations = new ArrayList<>();

        if (canCallLeftEdgeChow(tile) || canCallLeftDoubleSidedChow(tile)) {
            List<Tile> combination = new ArrayList<>();
            combination.add(tile.getPreviousTile().getPreviousTile());
            combination.add(tile);
            combination.add(tile.getPreviousTile());
            chowCombinations.add(combination);
        }

        if (canCallInsideStraightChow(tile)) {
            List<Tile> combination = new ArrayList<>();
            combination.add(tile.getPreviousTile());
            combination.add(tile);
            combination.add(tile.getNextTile());
            chowCombinations.add(combination);
        }

        if (canCallRightDoubleSidedChow(tile) || canCallRightEdgeChow(tile)) {
            List<Tile> combination = new ArrayList<>();
            combination.add(tile.getNextTile());
            combination.add(tile);
            combination.add(tile.getNextTile().getNextTile());
            chowCombinations.add(combination);
        }

        return chowCombinations;
    }

    private boolean canCallInsideStraightChow(Tile tile) {
        if (tile.getRank() == 1 || tile.getRank() == 9) {
            return false;
        }
        return hasTile(tile.getPreviousTile()) && hasTile(tile.getNextTile());
    }

    private boolean canCallEdgeChow(Tile tile) {
        return canCallLeftEdgeChow(tile) || canCallRightEdgeChow(tile);
    }

    private boolean canCallDoubleSidedChow(Tile tile) {
        return canCallLeftDoubleSidedChow(tile) || canCallRightDoubleSidedChow(tile);
    }

    private boolean canCallLeftEdgeChow(Tile tile) {
        return tile.getRank() == 3 && hasTile(tile.getPreviousTile()) && hasTile(tile.getPreviousTile().getPreviousTile());
    }

    private boolean canCallRightEdgeChow(Tile tile) {
        return tile.getRank() == 7 && hasTile(tile.getNextTile()) && hasTile(tile.getNextTile().getNextTile());
    }

    private boolean canCallLeftDoubleSidedChow(Tile tile) {
        return tile.getRank() > 3 && tile.getPreviousTile() != null && hasTile(tile.getPreviousTile()) && hasTile(tile.getPreviousTile().getPreviousTile());
    }

    private boolean canCallRightDoubleSidedChow(Tile tile) {
        return tile.getRank() < 7 && tile.getNextTile() != null && hasTile(tile.getNextTile()) && hasTile(tile.getNextTile().getNextTile());
    }
}
