package com.vincennlin.mahjongtrackerbackend.payload.game.operation;

import com.vincennlin.mahjongtrackerbackend.entity.game.GamePlayer;
import com.vincennlin.mahjongtrackerbackend.entity.game.Hand;

public interface GamePlayerOperationInterface {

    boolean canOperate(Hand hand, GamePlayer gamePlayer);
}
