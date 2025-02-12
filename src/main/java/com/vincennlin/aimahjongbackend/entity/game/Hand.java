package com.vincennlin.aimahjongbackend.entity.game;

import com.vincennlin.aimahjongbackend.entity.tile.BoardTile;
import com.vincennlin.aimahjongbackend.entity.tile.PlayerTile;
import com.vincennlin.aimahjongbackend.entity.tile.tilegroup.WallTileGroup;
import com.vincennlin.aimahjongbackend.payload.game.status.HandStatus;
import com.vincennlin.aimahjongbackend.payload.game.wind.Wind;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "hands")
public class Hand {

    public Hand(Round round, GamePlayer dealer, Wind prevailingWind) {
        this.round = round;
        this.dealer = dealer;
        this.activeGamePlayer = dealer;
        this.prevailingWind = prevailingWind;
        this.status = HandStatus.READY_TO_INITIALIZE_WALL_TILES;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private HandStatus status;

    @ManyToOne(
            fetch = FetchType.EAGER,
            cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.DETACH}
    )
    @JoinColumn(name = "round_id", referencedColumnName = "id")
    private Round round;

    @ManyToOne(
            fetch = FetchType.EAGER,
            cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.DETACH}
    )
    @JoinColumn(name = "dealer_id", referencedColumnName = "id")
    private GamePlayer dealer;

    @OneToOne(
            mappedBy = "hand",
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL
    )
    private WallTileGroup wallTileGroup;

    @OneToMany(
            mappedBy = "hand",
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL
    )
    private List<PlayerTile> playerTiles;

    @Enumerated(EnumType.STRING)
    @Column(name = "prevailing_wind")
    private Wind prevailingWind;

    @Column(name = "dice_number")
    private Integer diceNumber;

    @OneToOne(
            fetch = FetchType.EAGER,
            cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.DETACH}
    )
    @JoinColumn(name = "active_game_player_id", referencedColumnName = "id")
    private GamePlayer activeGamePlayer;

    @OneToOne(
            fetch = FetchType.EAGER,
            cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.DETACH}
    )
    @JoinColumn(name = "last_discarded_tile_id", referencedColumnName = "id")
    private BoardTile lastDiscardedTile;

    @ManyToOne(
            fetch = FetchType.EAGER,
            cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.DETACH}
    )
    @JoinColumn(name = "winner_id", referencedColumnName = "id")
    private GamePlayer winner;

    @ManyToOne(
            fetch = FetchType.EAGER,
            cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.DETACH}
    )
    @JoinColumn(name = "chucker_id", referencedColumnName = "id")
    private GamePlayer chucker;

    @Column(name = "is_extra_hand")
    private Boolean isExtraHand;

    @CreationTimestamp
    @Column(name = "date_created")
    private LocalDateTime dateCreated;

    public GamePlayer getNextDealer() {
        if (status != HandStatus.FINISHED) {
            return null;
        }
        return isExtraHand ? dealer : dealer.getDownwindPlayer();
    }

    public PlayerTile getPlayerTileByGamePlayer(GamePlayer gamePlayer) {
        return playerTiles.stream()
                .filter(playerTile -> playerTile.getGamePlayer().equals(gamePlayer))
                .findFirst()
                .orElse(null);
    }

    public GamePlayer getGamePlayerByUserId(Long userId) {
        return round.getGame().getGamePlayerByUserId(userId);
    }

    public GamePlayer getGamePlayerInGame(GamePlayer gamePlayer) {
        return round.getGame().getGamePlayerInGame(gamePlayer);
    }

    public Game getGame() {
        return round.getGame();
    }
}
