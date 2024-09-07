package com.vincennlin.mahjongtrackerbackend.repository.game;

import com.vincennlin.mahjongtrackerbackend.entity.game.Hand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HandRepository extends JpaRepository<Hand, Long> {
}
