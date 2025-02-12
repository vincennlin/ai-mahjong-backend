package com.vincennlin.aimahjongbackend.repository.game;

import com.vincennlin.aimahjongbackend.payload.game.playertype.PlayerType;
import com.vincennlin.aimahjongbackend.entity.game.Player;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlayerRepository extends JpaRepository<Player, Long> {

    Page<Player> findAllByUserId(Long userId, Pageable pageable);

    Optional<Player> findByUserIdAndType(Long userId, PlayerType type);

    Integer countByUserIdAndTypeIs(Long userId, PlayerType type);
}
