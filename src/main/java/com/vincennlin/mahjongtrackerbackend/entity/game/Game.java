package com.vincennlin.mahjongtrackerbackend.entity.game;

import com.vincennlin.mahjongtrackerbackend.constant.gamestatus.GameStatus;
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
@Table(name = "matches")
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private GameStatus status;

    @OneToMany(mappedBy = "game")
    private List<Round> rounds;

    @ManyToOne
    @JoinColumn(name = "east_player_id", referencedColumnName = "id")
    private User eastPlayer;

    @ManyToOne
    @JoinColumn(name = "south_player_id", referencedColumnName = "id")
    private User southPlayer;

    @ManyToOne
    @JoinColumn(name = "west_player_id", referencedColumnName = "id")
    private User westPlayer;

    @ManyToOne
    @JoinColumn(name = "north_player_id", referencedColumnName = "id")
    private User northPlayer;

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
