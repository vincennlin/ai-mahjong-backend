package com.vincennlin.mahjongtrackerbackend.service.game.impl;

import com.vincennlin.mahjongtrackerbackend.entity.game.GamePlayer;
import com.vincennlin.mahjongtrackerbackend.entity.game.Hand;
import com.vincennlin.mahjongtrackerbackend.entity.game.Operation;
import com.vincennlin.mahjongtrackerbackend.repository.game.OperationRepository;
import com.vincennlin.mahjongtrackerbackend.service.game.OperationService;
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
