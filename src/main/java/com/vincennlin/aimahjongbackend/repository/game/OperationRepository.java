package com.vincennlin.aimahjongbackend.repository.game;

import com.vincennlin.aimahjongbackend.entity.game.Operation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OperationRepository extends JpaRepository<Operation, Long> {
}
