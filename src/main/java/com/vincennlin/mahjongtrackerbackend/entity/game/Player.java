package com.vincennlin.mahjongtrackerbackend.entity.game;

import com.vincennlin.mahjongtrackerbackend.constant.playertype.PlayerType;
import com.vincennlin.mahjongtrackerbackend.entity.user.User;
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
@Table(name = "players")
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(name = "player_type")
    private PlayerType playerType;

    @ManyToOne
    @JoinColumn(name = "game_id", referencedColumnName = "id")
    private Game game;

    @ManyToOne
    @JoinColumn(name = "upwind_player_id", referencedColumnName = "id")
    private Player upwindPlayer;

    @ManyToOne
    @JoinColumn(name = "opposite_player_id", referencedColumnName = "id")
    private Player oppositePlayer;

    @ManyToOne
    @JoinColumn(name = "downwind_player_id", referencedColumnName = "id")
    private Player downwindPlayer;
}
