package com.vincennlin.aimahjongbackend.entity.tile.tilegroup;

import com.vincennlin.aimahjongbackend.entity.tile.BoardTile;
import com.vincennlin.aimahjongbackend.payload.tile.impl.Tile;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Transient
    private Map<Tile, Integer> tileCountMap;

    public Map<Tile, Integer> getTileCountMap() {
        if (tileCountMap == null) {
            tileCountMap = new HashMap<>();
            for (BoardTile boardTile : tiles) {
                Tile tile = boardTile.getTile();
                tileCountMap.put(tile, tileCountMap.getOrDefault(tile, 0) + 1);
            }
        }
        return tileCountMap;
    }

    public void addBoardTileToTileGroup(BoardTile boardTile) {
        if (boardTile != null) {
            boardTile.setTileGroup(this);
            tiles.add(boardTile);
            if (tileCountMap != null) {
                tileCountMap.put(boardTile.getTile(), tileCountMap.getOrDefault(boardTile.getTile(), 0) + 1);
            }
        }
    }

    public BoardTile removeTileFromTileGroup(BoardTile boardTile) {
        boardTile.setTileGroup(null);
        tiles.remove(boardTile);
        if (tileCountMap != null) {
            tileCountMap.put(boardTile.getTile(), tileCountMap.getOrDefault(boardTile.getTile(), 0) - 1);
            if (tileCountMap.get(boardTile.getTile()) == 0) {
                tileCountMap.remove(boardTile.getTile());
            }
        }
        return boardTile;
    }

    public BoardTile removeLastBoardTile() {
        if (tiles.isEmpty()) {
            return null;
        }
        BoardTile boardTile = tiles.remove(tiles.size() - 1);
        boardTile.setTileGroup(null);
        if (tileCountMap != null) {
            tileCountMap.put(boardTile.getTile(), tileCountMap.getOrDefault(boardTile.getTile(), 0) - 1);
            if (tileCountMap.get(boardTile.getTile()) == 0) {
                tileCountMap.remove(boardTile.getTile());
            }
        }
        return boardTile;
    }

    public BoardTile removeFirstBoardTileByTile(Tile tile) {
        BoardTile boardTileToRemove = tiles.stream()
                .filter(boardTile -> boardTile.getTile().equals(tile))
                .findFirst()
                .orElse(null);
        if (boardTileToRemove != null) {
            tiles.remove(boardTileToRemove);
            boardTileToRemove.setTileGroup(null);
            if (tileCountMap != null) {
                tileCountMap.put(boardTileToRemove.getTile(), tileCountMap.getOrDefault(boardTileToRemove.getTile(), 0) - 1);
                if (tileCountMap.get(boardTileToRemove.getTile()) == 0) {
                    tileCountMap.remove(boardTileToRemove.getTile());
                }
            }
            return boardTileToRemove;
        } else {
            return null;
        }
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
}
