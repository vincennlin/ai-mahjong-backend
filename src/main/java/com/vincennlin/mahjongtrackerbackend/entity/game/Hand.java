package com.vincennlin.mahjongtrackerbackend.entity.game;

import com.vincennlin.mahjongtrackerbackend.entity.tile.PlayerTile;
import com.vincennlin.mahjongtrackerbackend.entity.tile.tilegroup.WallTileGroup;
import com.vincennlin.mahjongtrackerbackend.payload.game.status.HandStatus;
import com.vincennlin.mahjongtrackerbackend.payload.game.wind.Wind;
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
        this.prevailingWind = prevailingWind;
        this.status = HandStatus.READY_TO_INITIALIZE_WALL_TILES;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private HandStatus status;

    @ManyToOne
    @JoinColumn(name = "round_id", referencedColumnName = "id")
    private Round round;

    @ManyToOne
    @JoinColumn(name = "dealer_id", referencedColumnName = "id")
    private GamePlayer dealer;

    @OneToOne(mappedBy = "hand")
    private WallTileGroup wallTileGroup;

    @OneToMany(mappedBy = "hand", fetch = FetchType.EAGER)
    private List<PlayerTile> playerTiles;

    @Enumerated(EnumType.STRING)
    @Column(name = "prevailing_wind")
    private Wind prevailingWind;

    @Column(name = "dice_number")
    private Integer diceNumber;

    @ManyToOne
    @JoinColumn(name = "winner_id", referencedColumnName = "id")
    private GamePlayer winner;

    @ManyToOne
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
}
