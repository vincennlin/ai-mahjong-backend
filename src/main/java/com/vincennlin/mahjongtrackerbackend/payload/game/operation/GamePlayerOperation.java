package com.vincennlin.mahjongtrackerbackend.payload.game.operation;

import com.vincennlin.mahjongtrackerbackend.entity.game.Hand;
import com.vincennlin.mahjongtrackerbackend.entity.tile.tilegroup.HandTileGroup;
import com.vincennlin.mahjongtrackerbackend.payload.game.status.GamePlayerStatus;
import lombok.Getter;

@Getter
public enum GamePlayerOperation implements GamePlayerOperationInterface {

    CALL_FOR_CHOW(GamePlayerStatus.THINKING_FOR_DISCARD) {
        @Override
        public boolean canOperate(Hand hand, HandTileGroup handTileGroup) {
            return handTileGroup.canCallChow(hand.getActiveGamePlayer(), hand.getLastDiscardedTile().getTile());
        }
    },
    CALL_FOR_PONG(GamePlayerStatus.THINKING_FOR_DISCARD) {
        @Override
        public boolean canOperate(Hand hand, HandTileGroup handTileGroup) {
            return handTileGroup.canCallPong(hand.getActiveGamePlayer(), hand.getLastDiscardedTile().getTile());
        }
    },
    CALL_FOR_EXPOSED_KONG(GamePlayerStatus.THINKING_FOR_DISCARD) {
        @Override
        public boolean canOperate(Hand hand, HandTileGroup handTileGroup) {
            return handTileGroup.canCallKong(hand.getActiveGamePlayer(), hand.getLastDiscardedTile().getTile());
        }
    },
    CANCEL(GamePlayerStatus.WAITING) {
        @Override
        public boolean canOperate(Hand hand, HandTileGroup handTileGroup) {
            return true;
        }
    },

    DRAW_TILE(GamePlayerStatus.THINKING_FOR_DISCARD) {
        @Override
        public boolean canOperate(Hand hand, HandTileGroup handTileGroup) {
            return true;
        }
    },

    DISCARD(GamePlayerStatus.WAITING) {
        @Override
        public boolean canOperate(Hand hand, HandTileGroup handTileGroup) {
            return true;
        }
    },
    CALL_FOR_CONCEALED_KONG(GamePlayerStatus.THINKING_FOR_DISCARD) {
        @Override
        public boolean canOperate(Hand hand, HandTileGroup handTileGroup) {
            return handTileGroup.canCallConcealedKong();
        }
    },
    CALL_FOR_ADDED_KONG(GamePlayerStatus.THINKING_FOR_DISCARD) {
        @Override
        public boolean canOperate(Hand hand, HandTileGroup handTileGroup) {
            return handTileGroup.canCallAddedKong();
        }
    },

    CALL_FOR_WIN(GamePlayerStatus.WON) {
        @Override
        public boolean canOperate(Hand hand, HandTileGroup handTileGroup) {
            return handTileGroup.canCallWin();
        }
    },
    CALL_FOR_SELF_DRAW(GamePlayerStatus.SELF_DRAWN_WIN) {
        @Override
        public boolean canOperate(Hand hand, HandTileGroup handTileGroup) {
            return handTileGroup.canCallSelfDraw();
        }
    };

    private final GamePlayerStatus nextStatus;

    GamePlayerOperation(GamePlayerStatus nextStatus) {
        this.nextStatus = nextStatus;
    }
}
