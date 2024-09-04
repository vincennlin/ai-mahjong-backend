package com.vincennlin.mahjongtrackerbackend.entity.tile;

import com.vincennlin.mahjongtrackerbackend.entity.tile.tilegroup.TileGroup;
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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "tile_id", referencedColumnName = "id")
    private Tile tile;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "tile_group_id", referencedColumnName = "id")
    private TileGroup tileGroup;
}
