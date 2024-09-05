package com.vincennlin.mahjongtrackerbackend.entity.tile.tilegroup;

import com.vincennlin.mahjongtrackerbackend.entity.tile.PlayerTile;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "hand_tile_groups")
public class HandTileGroup extends TileGroup implements PlayerTileGroup {

    @OneToOne(mappedBy = "handTiles")
    @JoinColumn(name = "player_tile_id", referencedColumnName = "id")
    private PlayerTile playerTile;

    @Override
    public Long getPlayerId() {
        return playerTile.getGamePlayer().getId();
    }
}
