package com.vincennlin.mahjongtrackerbackend.payload.game.operation;

import com.vincennlin.mahjongtrackerbackend.entity.game.Hand;
import com.vincennlin.mahjongtrackerbackend.entity.tile.tilegroup.HandTileGroup;

public interface GamePlayerOperationInterface {

    boolean canOperate(Hand hand, HandTileGroup handTileGroup);
}
