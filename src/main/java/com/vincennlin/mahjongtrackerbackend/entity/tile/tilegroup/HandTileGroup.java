package com.vincennlin.mahjongtrackerbackend.entity.tile.tilegroup;

import com.vincennlin.mahjongtrackerbackend.entity.tile.BoardTile;
import com.vincennlin.mahjongtrackerbackend.entity.tile.PlayerTile;
import com.vincennlin.mahjongtrackerbackend.payload.tile.TileComparator;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Comparator;

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

    @OneToOne(mappedBy = "handTiles")
    @JoinColumn(name = "player_tile_id", referencedColumnName = "id")
    private PlayerTile playerTile;

    @Override
    public Long getPlayerId() {
        return playerTile.getGamePlayer().getId();
    }

    public void sortHandTiles() {
        getTiles().sort(new TileComparator());
    }

    public boolean containsFlowerTile() {
        return getTiles().stream().anyMatch(BoardTile::isFlower);
    }
}
