package com.vincennlin.aimahjongbackend.entity.tile.tilegroup;

import com.vincennlin.aimahjongbackend.entity.tile.PlayerTile;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "flower_tile_groups")
public class FlowerTileGroup extends TileGroup implements PlayerTileGroup {

    public FlowerTileGroup(PlayerTile playerTile) {
        super();
        this.playerTile = playerTile;
    }

    @OneToOne(
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL
    )
    @JoinColumn(name = "player_tile_id", referencedColumnName = "id")
    private PlayerTile playerTile;
}
