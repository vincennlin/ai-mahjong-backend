package com.vincennlin.mahjongtrackerbackend.entity.game;

import com.vincennlin.mahjongtrackerbackend.constant.wind.Wind;
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
@Table(name = "rounds")
public class Round {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
}
