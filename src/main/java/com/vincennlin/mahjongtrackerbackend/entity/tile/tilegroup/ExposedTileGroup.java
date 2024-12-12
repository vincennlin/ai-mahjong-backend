package com.vincennlin.mahjongtrackerbackend.entity.tile.tilegroup;

import com.vincennlin.mahjongtrackerbackend.entity.tile.PlayerTile;
import com.vincennlin.mahjongtrackerbackend.payload.board.MeldType;
import com.vincennlin.mahjongtrackerbackend.payload.tile.impl.Tile;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "exposed_tile_groups")
public class ExposedTileGroup extends TileGroup implements PlayerTileGroup {

    public ExposedTileGroup(PlayerTile playerTile, MeldType meldType) {
        super();
        this.playerTile = playerTile;
        this.meldType = meldType;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(
            fetch = FetchType.EAGER,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH}
    )
    @JoinColumn(name = "player_tile_id", referencedColumnName = "id")
    private PlayerTile playerTile;

    @Enumerated(EnumType.STRING)
    @Column(name = "meld_type")
    private MeldType meldType;

    public boolean isPongForTile(Tile tile) {
        return meldType == MeldType.PONG
                && getTiles().size() == 3
                && getTiles().stream().allMatch(boardTile -> boardTile.getTile().equals(tile));
    }
}
