package com.vincennlin.aimahjongbackend.payload.game.operation;

import com.vincennlin.aimahjongbackend.entity.game.GamePlayer;
import com.vincennlin.aimahjongbackend.entity.game.Hand;

public interface GamePlayerOperationInterface {

    boolean canOperate(Hand hand, GamePlayer gamePlayer);
}
