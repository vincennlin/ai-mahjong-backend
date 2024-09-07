package com.vincennlin.mahjongtrackerbackend.repository.game;

import com.vincennlin.mahjongtrackerbackend.entity.game.Operation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OperationRepository extends JpaRepository<Operation, Long> {
}
