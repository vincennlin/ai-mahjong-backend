package com.vincennlin.mahjongtrackerbackend.entity.game;

import com.vincennlin.mahjongtrackerbackend.constant.game.gamestatus.GameStatus;
import com.vincennlin.mahjongtrackerbackend.entity.user.User;
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
@Table(name = "games")
public class Game {

    public Game(User creator) {
        this.status = GameStatus.WAITING_FOR_PLAYERS;
        this.creator = creator;
        this.basePoint = 3;
        this.fannPoint = 1;
        this.dollarPerPoint = 10;
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

    @OneToMany(mappedBy = "game")
    private List<GamePlayer> players;

    @ManyToOne
    @JoinColumn(name = "east_player_id", referencedColumnName = "id")
    private Player eastPlayer;

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
}
