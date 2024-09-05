package com.vincennlin.mahjongtrackerbackend.entity.tile;

import com.vincennlin.mahjongtrackerbackend.entity.tile.tilegroup.TileGroup;
import com.vincennlin.mahjongtrackerbackend.payload.tile.impl.Tile;
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
@Table(name = "board_tiles")
public class BoardTile {

    public BoardTile(Tile tile, TileGroup tileGroup) {
        this.tile = tile;
        this.tileGroup = tileGroup;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "tile")
    private Tile tile;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "tile_group_id", referencedColumnName = "id")
    private TileGroup tileGroup;
}
