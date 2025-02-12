package com.vincennlin.aimahjongbackend.service.game;

import com.vincennlin.aimahjongbackend.entity.game.GamePlayer;
import com.vincennlin.aimahjongbackend.entity.game.Hand;
import com.vincennlin.aimahjongbackend.entity.game.Operation;

public interface OperationService {

    Operation createOperation(Hand hand, GamePlayer gamePlayer);

    Operation saveOperation(Operation operation);
}
