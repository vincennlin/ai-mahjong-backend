package com.vincennlin.aimahjongbackend.entity.tile;

import com.vincennlin.aimahjongbackend.entity.game.GamePlayer;
import com.vincennlin.aimahjongbackend.entity.game.Hand;
import com.vincennlin.aimahjongbackend.entity.tile.tilegroup.*;
import com.vincennlin.aimahjongbackend.payload.tile.impl.Tile;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "player_tiles")
public class PlayerTile {

    public PlayerTile(Hand hand, GamePlayer gamePlayer) {
        this.hand = hand;
        this.gamePlayer = gamePlayer;
        this.exposedTiles = new ArrayList<>();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "hand_id", referencedColumnName = "id")
    private Hand hand;

    @ManyToOne(
            fetch = FetchType.EAGER,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH}
    )
    @JoinColumn(name = "game_player_id", referencedColumnName = "id")
    private GamePlayer gamePlayer;

    @OneToOne(
            fetch = FetchType.EAGER,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH}
    )
    private HandTileGroup handTiles;

    @OneToOne(
            fetch = FetchType.EAGER,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH}
    )
    private FlowerTileGroup flowerTiles;

    @OneToMany(
            mappedBy = "playerTile",
            fetch = FetchType.EAGER,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH}
    )
    private List<ExposedTileGroup> exposedTiles;

    @OneToOne(
            mappedBy = "playerTile",
            fetch = FetchType.EAGER,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH}
    )
    private DiscardedTileGroup discardedTiles;

    @OneToOne(
            mappedBy = "playerTile",
            fetch = FetchType.EAGER,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH}
    )
    private DrawnTileGroup drawnTiles;

    public String[] convertTilesToString(TileGroup tileGroup) {
        return tileGroup.convertTilesToString();
    }

    public void setLastDrawnTileToPlayerTileGroup(BoardTile boardTile) {
        if (boardTile.isFlower()) {
            flowerTiles.addBoardTileToTileGroup(boardTile);
            drawnTiles.setDrawnTile(null, true);
        } else {
            drawnTiles.setDrawnTile(boardTile, false);
        }
    }

    public boolean hasPongMeldForTile(Tile tile) {
        return exposedTiles.stream().anyMatch(exposedTileGroup -> exposedTileGroup.isPongForTile(tile));
    }

    public void pongTile(ExposedTileGroup exposedTiles, BoardTile boardTile) {
        exposedTiles.addBoardTileToTileGroup(handTiles.removeFirstBoardTileByTile(boardTile.getTile()), 'l');

        exposedTiles.addBoardTileToTileGroup(boardTile, 'm');

        exposedTiles.addBoardTileToTileGroup(handTiles.removeFirstBoardTileByTile(boardTile.getTile()), 'r');

        getExposedTiles().add(exposedTiles);
    }

    public void chowTile(ExposedTileGroup exposedTiles, BoardTile boardTile, List<Tile> chowCombination) {
        chowCombination.remove(1);

        exposedTiles.addBoardTileToTileGroup(handTiles.removeFirstBoardTileByTile(chowCombination.remove(0)), 'l');

        exposedTiles.addBoardTileToTileGroup(boardTile, 'm');

        exposedTiles.addBoardTileToTileGroup(handTiles.removeFirstBoardTileByTile(chowCombination.remove(0)), 'r');

        getExposedTiles().add(exposedTiles);
    }

    public void exposeKongTile(ExposedTileGroup exposedTiles, BoardTile boardTile) {
        exposedTiles.addBoardTileToTileGroup(handTiles.removeFirstBoardTileByTile(boardTile.getTile()), 'l');

        exposedTiles.addBoardTileToTileGroup(handTiles.removeFirstBoardTileByTile(boardTile.getTile()), 'm');

        exposedTiles.addBoardTileToTileGroup(handTiles.removeFirstBoardTileByTile(boardTile.getTile()), 'r');

        exposedTiles.addBoardTileToTileGroup(boardTile, 't');

        getExposedTiles().add(exposedTiles);
    }

    public void concealKongTile(ExposedTileGroup exposedTiles, BoardTile boardTile) {
        exposedTiles.addBoardTileToTileGroup(handTiles.removeFirstBoardTileByTile(boardTile.getTile()), 'l');

        exposedTiles.addBoardTileToTileGroup(handTiles.removeFirstBoardTileByTile(boardTile.getTile()), 'm');

        exposedTiles.addBoardTileToTileGroup(handTiles.removeFirstBoardTileByTile(boardTile.getTile()), 'r');

        exposedTiles.addBoardTileToTileGroup(boardTile, 't');

        getExposedTiles().add(exposedTiles);
    }
}
