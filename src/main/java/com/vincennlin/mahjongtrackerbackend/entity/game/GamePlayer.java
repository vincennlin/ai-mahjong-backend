package com.vincennlin.mahjongtrackerbackend.entity.game;

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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "game_id", referencedColumnName = "id")
    private Game game;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "player_id", referencedColumnName = "id")
    private Player player;

    @OneToOne
    @JoinColumn(name = "downwind_player_id", referencedColumnName = "id")
    private GamePlayer downwindPlayer;

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
}
