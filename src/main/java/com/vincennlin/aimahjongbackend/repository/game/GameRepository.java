package com.vincennlin.aimahjongbackend.repository.game;

import com.vincennlin.aimahjongbackend.entity.game.Game;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game, Long> {

    Page<Game> findAllByCreatorId(Long creatorId, Pageable pageable);
}
