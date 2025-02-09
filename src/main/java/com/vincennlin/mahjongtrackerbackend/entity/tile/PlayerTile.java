package com.vincennlin.mahjongtrackerbackend.entity.tile;

import com.vincennlin.mahjongtrackerbackend.entity.game.GamePlayer;
import com.vincennlin.mahjongtrackerbackend.entity.game.Hand;
import com.vincennlin.mahjongtrackerbackend.entity.tile.tilegroup.*;
import com.vincennlin.mahjongtrackerbackend.payload.tile.impl.Tile;
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

    public String[] convertTilesToString(TileGroup tileGroup) {
        return tileGroup.convertTilesToString();
    }

    public void addBoardTileToTileGroup(BoardTile boardTile) {
        if (boardTile.isFlower()) {
            flowerTiles.addBoardTileToTileGroup(boardTile);
        } else {
            handTiles.addBoardTileToTileGroup(boardTile);
        }
    }

    public boolean hasPongMeldForTile(Tile tile) {
        return exposedTiles.stream().anyMatch(exposedTileGroup -> exposedTileGroup.isPongForTile(tile));
    }

    public void pongTile(ExposedTileGroup exposedTiles, BoardTile boardTile) {
        BoardTile tile1 = handTiles.removeFirstBoardTileByTile(boardTile.getTile());
        exposedTiles.addBoardTileToTileGroup(tile1, 'l');

        exposedTiles.addBoardTileToTileGroup(boardTile, 'm');

        BoardTile tile2 = handTiles.removeFirstBoardTileByTile(boardTile.getTile());
        exposedTiles.addBoardTileToTileGroup(tile2, 'r');

        getExposedTiles().add(exposedTiles);
    }

    public void chowTile(ExposedTileGroup exposedTiles, BoardTile boardTile, List<Tile> chowCombination) {
        chowCombination.remove(1);

        BoardTile tile1 = handTiles.removeFirstBoardTileByTile(chowCombination.remove(0));
        exposedTiles.addBoardTileToTileGroup(tile1, 'l');

        exposedTiles.addBoardTileToTileGroup(boardTile, 'm');

        BoardTile tile2 = handTiles.removeFirstBoardTileByTile(chowCombination.remove(0));
        exposedTiles.addBoardTileToTileGroup(tile2, 'r');

        getExposedTiles().add(exposedTiles);
    }
}
