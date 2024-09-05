package com.vincennlin.mahjongtrackerbackend.entity.game;

import com.vincennlin.mahjongtrackerbackend.payload.game.status.GameStatus;
import com.vincennlin.mahjongtrackerbackend.entity.user.User;
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
@Table(name = "games")
public class Game {

    public Game(User creator) {
        this.status = GameStatus.WAITING_FOR_PLAYERS;
        this.creator = creator;
        this.gamePlayers = new ArrayList<>();
        this.basePoint = 3;
        this.fannPoint = 1;
        this.dollarPerPoint = 10;
        this.rounds = new ArrayList<>();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "creator_id", referencedColumnName = "id")
    private User creator;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private GameStatus status;

    @OneToMany(
            mappedBy = "game",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    private List<GamePlayer> gamePlayers;

    @ManyToOne
    @JoinColumn(name = "east_player_id", referencedColumnName = "id")
    private GamePlayer eastPlayer;

    @OneToMany(mappedBy = "game")
    private List<Round> rounds;

    @Column(name = "base_point")
    private int basePoint;

    @Column(name = "fann_point")
    private int fannPoint;

    @Column(name = "dollar_per_point")
    private int dollarPerPoint;

    @CreationTimestamp
    @Column(name = "date_created")
    private LocalDateTime dateCreated;

    public boolean containsPlayerById(Long playerId) {
        for (GamePlayer gamePlayer : gamePlayers) {
            if (gamePlayer.getPlayer().getId().equals(playerId)) {
                return true;
            }
        }
        return false;
    }

    public Wind getNextRoundWind() {
        if (rounds == null || rounds.isEmpty()) {
            return Wind.EAST;
        }
        return rounds.get(rounds.size() - 1).getRoundWind().nextWind();
    }

    public Round getCurrentRound() {
        if (rounds == null || rounds.isEmpty()) {
            return null;
        }
        return rounds.get(rounds.size() - 1);
    }

    public Hand getCurrentHand() {
        return getCurrentRound().getCurrentHand();
    }
}
