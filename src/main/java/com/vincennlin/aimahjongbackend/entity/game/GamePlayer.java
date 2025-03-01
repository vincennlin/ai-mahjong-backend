package com.vincennlin.aimahjongbackend.entity.game;

import com.vincennlin.aimahjongbackend.entity.tile.PlayerTile;
import com.vincennlin.aimahjongbackend.entity.tile.tilegroup.HandTileGroup;
import com.vincennlin.aimahjongbackend.payload.game.status.GamePlayerStatus;
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
@Table(name = "game_players")
public class GamePlayer {

    public GamePlayer(Game game, Player player) {
        this.game = game;
        this.player = player;
        this.status = GamePlayerStatus.WAITING;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "game_id", referencedColumnName = "id")
    private Game game;

    @ManyToOne(
            fetch = FetchType.EAGER,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH}
    )
    @JoinColumn(name = "player_id", referencedColumnName = "id")
    private Player player;

    @OneToOne(
            fetch = FetchType.EAGER,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH}
    )
    @JoinColumn(name = "downwind_player_id", referencedColumnName = "id")
    private GamePlayer downwindPlayer;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private GamePlayerStatus status;

//    @Column(name = "score")
//    private int score;
//
//    @Column(name = "winning")
//    private int winning;
//
//    @Column(name = "self_drawn")
//    private int selfDrawn;
//
//    @Column(name = "chunk")
//    private int chunk;
//
//    @Column(name = "self_drawn_against")
//    private int selfDrawnAgainst;

    public int getPositionIndex() {
        return game.getGamePlayers().indexOf(this);
    }

    public GamePlayer getOppositePlayer() {
        return getDownwindPlayer().getDownwindPlayer();
    }

    public GamePlayer getUpwindPlayer() {
        return getOppositePlayer().getDownwindPlayer();
    }

    public PlayerTile getPlayerTile() {
        return game.getPlayerTileByGamePlayer(this);
    }

    public HandTileGroup getHandTiles() {
        return getPlayerTile().getHandTiles();
    }
}
