package com.vincennlin.mahjongtrackerbackend.entity.tile;

import com.vincennlin.mahjongtrackerbackend.payload.tile.type.TileType;
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
@Table(name = "tiles")
public class Tile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private TileType type;

    @Column(name = "tileRank")
    private int tileRank;
}
