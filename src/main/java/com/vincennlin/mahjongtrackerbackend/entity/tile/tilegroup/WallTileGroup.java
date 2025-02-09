package com.vincennlin.mahjongtrackerbackend.entity.tile.tilegroup;

import com.vincennlin.mahjongtrackerbackend.entity.game.Hand;
import com.vincennlin.mahjongtrackerbackend.entity.tile.BoardTile;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "wall_tile_groups")
public class WallTileGroup extends TileGroup {

    public WallTileGroup(Hand hand) {
        super();
        this.hand = hand;
    }

    @OneToOne(
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL
    )
    private Hand hand;

    public BoardTile drawTileFromWall(boolean isFromHead) {
        if (getTiles().isEmpty()) {
            return null;
        }
        return getTiles().remove(isFromHead ? 0 : getTiles().size() - 1);
    }
}
