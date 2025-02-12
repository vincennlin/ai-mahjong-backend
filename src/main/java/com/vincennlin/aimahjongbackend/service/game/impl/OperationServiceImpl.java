package com.vincennlin.aimahjongbackend.service.game.impl;

import com.vincennlin.aimahjongbackend.entity.game.GamePlayer;
import com.vincennlin.aimahjongbackend.entity.game.Hand;
import com.vincennlin.aimahjongbackend.entity.game.Operation;
import com.vincennlin.aimahjongbackend.repository.game.OperationRepository;
import com.vincennlin.aimahjongbackend.service.game.OperationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class OperationServiceImpl implements OperationService {

    private final OperationRepository operationRepository;

    @Override
    public Operation createOperation(Hand hand, GamePlayer gamePlayer) {

        Operation operation = new Operation(hand, gamePlayer);

        return operationRepository.save(operation);
    }

    @Override
    public Operation saveOperation(Operation operation) {

        return operationRepository.save(operation);
    }
}
