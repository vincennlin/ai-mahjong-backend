package com.vincennlin.mahjongtrackerbackend.repository.game;

import com.vincennlin.mahjongtrackerbackend.constant.game.playertype.PlayerType;
import com.vincennlin.mahjongtrackerbackend.entity.game.Player;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, Long> {

    Page<Player> findAllByUserId(Long userId, Pageable pageable);

    Integer countByUserIdAndTypeIs(Long userId, PlayerType type);
}
