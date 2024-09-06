package com.vincennlin.mahjongtrackerbackend.entity.game;

import com.vincennlin.mahjongtrackerbackend.payload.game.status.HandStatus;
import com.vincennlin.mahjongtrackerbackend.payload.game.status.RoundStatus;
import com.vincennlin.mahjongtrackerbackend.payload.game.wind.Wind;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "rounds")
public class Round {

    public Round(Game game, Wind roundWind) {
        this.game = game;
        this.roundWind = roundWind;
        this.hands = new ArrayList<>();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private RoundStatus status;

    @OneToMany(
            mappedBy = "round",
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL
    )
    private List<Hand> hands;

    @ManyToOne(
            fetch = FetchType.EAGER,
            cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.DETACH}
    )
    @JoinColumn(name = "game_id", referencedColumnName = "id")
    private Game game;

    @Enumerated(EnumType.STRING)
    @Column(name = "round_wind")
    private Wind roundWind;

    @CreationTimestamp
    @Column(name = "date_created")
    private LocalDateTime dateCreated;

    public Hand getCurrentHand() {
        if (hands.isEmpty()) {
            return null;
        }
        Hand currentHand = hands.get(hands.size() - 1);
        if (currentHand.getStatus() != HandStatus.FINISHED) {
            return currentHand;
        } else {
            return null;
        }
    }

    public Hand getPreivousHand() {
        if (hands.isEmpty()) {
            return null;
        }
        Hand previousHand = hands.get(hands.size() - 1);
        if (previousHand.getStatus() == HandStatus.FINISHED) {
            return previousHand;
        } else if (hands.size() > 1) {
            return hands.get(hands.size() - 2);
        } else {
            return null;
        }
    }

    public Wind getNextPrevailingWind() {
        if (hands.isEmpty()) {
            return Wind.EAST;
        }
        return getPreivousHand().getPrevailingWind().nextWind();
    }

    public GamePlayer getNextDealer() {
        if (hands.isEmpty()) {
            return game.getEastPlayer();
        }
        return getPreivousHand().getNextDealer();
    }
}
