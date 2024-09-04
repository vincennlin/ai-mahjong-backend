package com.vincennlin.mahjongtrackerbackend.entity.tile;

import com.vincennlin.mahjongtrackerbackend.entity.game.GamePlayer;
import com.vincennlin.mahjongtrackerbackend.entity.game.Hand;
import com.vincennlin.mahjongtrackerbackend.entity.tile.tilegroup.*;
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
@Table(name = "player_tiles")
public class PlayerTile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "hand_id", referencedColumnName = "id")
    private Hand hand;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "game_player_id", referencedColumnName = "id")
    private GamePlayer gamePlayer;

    @OneToOne(fetch = FetchType.EAGER)
    private HandTileGroup handTiles;

    @OneToOne(fetch = FetchType.EAGER)
    private ExposedTileGroup exposedTiles;

    @OneToOne(fetch = FetchType.EAGER)
    private DiscardedTileGroup discardedTiles;
}
