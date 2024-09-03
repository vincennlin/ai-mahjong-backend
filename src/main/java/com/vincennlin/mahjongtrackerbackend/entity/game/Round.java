package com.vincennlin.mahjongtrackerbackend.entity.game;

import com.vincennlin.mahjongtrackerbackend.payload.game.gamestatus.HandStatus;
import com.vincennlin.mahjongtrackerbackend.payload.game.gamestatus.RoundStatus;
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

    @OneToMany(mappedBy = "round")
    private List<Hand> hands;

    @ManyToOne
    @JoinColumn(name = "game_id", referencedColumnName = "id")
    private Game game;

    @Enumerated(EnumType.STRING)
    @Column(name = "round_wind")
    private Wind roundWind;

    @CreationTimestamp
    @Column(name = "date_created")
    private LocalDateTime dateCreated;

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
