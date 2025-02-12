package com.vincennlin.aimahjongbackend.entity.game;

import com.vincennlin.aimahjongbackend.entity.tile.BoardTile;
import com.vincennlin.aimahjongbackend.entity.tile.tilegroup.TileGroup;
import com.vincennlin.aimahjongbackend.payload.game.operation.GamePlayerOperation;
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
@Table(name = "operations")
public class Operation {

    public Operation(Hand hand, GamePlayer player) {
        this.hand = hand;
        this.player = player;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(
            fetch = FetchType.EAGER,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH}
    )
    @JoinColumn(name = "hand_id", referencedColumnName = "id")
    private Hand hand;

    @ManyToOne(
            fetch = FetchType.EAGER,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH}
    )
    @JoinColumn(name = "game_player_id", referencedColumnName = "id")
    private GamePlayer player;

    @Enumerated(EnumType.STRING)
    @Column(name = "game_player_operation")
    private GamePlayerOperation gamePlayerOperation;

    @ManyToOne(
            fetch = FetchType.EAGER,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH}
    )
    private BoardTile boardTile;

    @ManyToOne(
            fetch = FetchType.EAGER,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH}
    )
    private TileGroup previousTileGroup;
}
