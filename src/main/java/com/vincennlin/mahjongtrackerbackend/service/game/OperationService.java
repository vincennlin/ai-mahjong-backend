package com.vincennlin.mahjongtrackerbackend.service.game;

import com.vincennlin.mahjongtrackerbackend.entity.game.GamePlayer;
import com.vincennlin.mahjongtrackerbackend.entity.game.Hand;
import com.vincennlin.mahjongtrackerbackend.entity.game.Operation;
import com.vincennlin.mahjongtrackerbackend.entity.tile.BoardTile;

public interface OperationService {

    Operation createOperation(Hand hand, GamePlayer gamePlayer);

    Operation saveOperation(Operation operation);
}
