package com.vincennlin.mahjongtrackerbackend.entity.tile.tilegroup;

import com.vincennlin.mahjongtrackerbackend.entity.tile.BoardTile;
import com.vincennlin.mahjongtrackerbackend.payload.tile.impl.Tile;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@Entity
@Table(name = "tile_groups")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class TileGroup {

    public TileGroup() {
        this.tiles = new ArrayList<>();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(
            mappedBy = "tileGroup",
            fetch = FetchType.EAGER,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH}
    )
    private List<BoardTile> tiles;

    public void addBoardTileToTileGroup(BoardTile boardTile) {
        boardTile.setTileGroup(this);
        tiles.add(boardTile);
    }

    public BoardTile removeTileFromTileGroup(BoardTile boardTile) {
        boardTile.setTileGroup(null);
        tiles.remove(boardTile);
        return boardTile;
    }

    public String[] convertTilesToString() {
        StringBuilder tilesNumSb = new StringBuilder();
        StringBuilder tilesSubSb = new StringBuilder();
        Tile previousTile = null;
        for (BoardTile boardTile : getTiles()) {
            Tile tile = boardTile.getTile();
            if (previousTile != null && (tile.getSubTileType() != previousTile.getSubTileType())) {
                tilesNumSb.append(" ");
                tilesSubSb.append(" ");
            }
            tilesNumSb.append(tile.getName().charAt(0));
            if (tile.getName().length() >= 2) {
                tilesSubSb.append(tile.getName().charAt(1));
            } else if (tile.getName().length() == 1) {
                tilesSubSb.append("＝");
            }
            previousTile = tile;
        }
        return new String[]{tilesNumSb.toString(), tilesSubSb.toString()};
    }

    public BoardTile removeLastBoardTile() {
        if (tiles.isEmpty()) {
            return null;
        }
        return tiles.remove(tiles.size() - 1);
    }

    public BoardTile removeFirstBoardTileByTile(Tile tile) {
        BoardTile boardTileToRemove = tiles.stream()
                .filter(boardTile -> boardTile.getTile().equals(tile))
                .findFirst()
                .orElse(null);
        if (boardTileToRemove != null) {
            tiles.remove(boardTileToRemove);
            return boardTileToRemove;
        } else {
            return null;
        }
    }
}
