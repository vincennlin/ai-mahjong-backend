package com.vincennlin.mahjongtrackerbackend.entity.tile.tilegroup;

import com.vincennlin.mahjongtrackerbackend.entity.tile.BoardTile;
import com.vincennlin.mahjongtrackerbackend.entity.tile.PlayerTile;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "drawn_tile_groups")
public class DrawnTileGroup extends TileGroup implements PlayerTileGroup {

    public DrawnTileGroup(PlayerTile playerTile) {
        super();
        this.playerTile = playerTile;
    }

    @OneToOne(
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL
    )
    @JoinColumn(name = "player_tile_id", referencedColumnName = "id")
    private PlayerTile playerTile;

    @Transient
    private boolean isFoulHand;

    public BoardTile getDrawnTile() {
        return getTiles().isEmpty() ? null : getTiles().get(0);
    }

    public boolean hasDrawnTile() {
        return !getTiles().isEmpty();
    }

    public void setDrawnTile(BoardTile boardTile, boolean isFoulHand) {
        clearDrawnTile();
        if (boardTile != null) {
            getTiles().add(boardTile);
            boardTile.setTileGroup(this);
        }
        this.isFoulHand = isFoulHand;
    }

    public void clearDrawnTile() {
        getTiles().clear();
        this.isFoulHand = false;
    }
}
