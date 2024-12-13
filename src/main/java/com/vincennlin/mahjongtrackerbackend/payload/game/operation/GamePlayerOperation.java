package com.vincennlin.mahjongtrackerbackend.payload.game.operation;

import com.vincennlin.mahjongtrackerbackend.entity.game.GamePlayer;
import com.vincennlin.mahjongtrackerbackend.entity.game.Hand;
import com.vincennlin.mahjongtrackerbackend.payload.game.status.GamePlayerStatus;
import lombok.Getter;

@Getter
public enum GamePlayerOperation implements GamePlayerOperationInterface {

    CALL_FOR_CHOW(GamePlayerStatus.THINKING_FOR_DISCARD) {
        @Override
        public boolean canOperate(Hand hand, GamePlayer gamePlayer) {
            return gamePlayer.getHandTiles().canCallChow(hand.getActiveGamePlayer(), hand.getLastDiscardedTile().getTile());
        }
    },
    CALL_FOR_PONG(GamePlayerStatus.THINKING_FOR_DISCARD) {
        @Override
        public boolean canOperate(Hand hand, GamePlayer gamePlayer) {
            return gamePlayer.getHandTiles().canCallPong(hand.getActiveGamePlayer(), hand.getLastDiscardedTile().getTile());
        }
    },
    CALL_FOR_EXPOSED_KONG(GamePlayerStatus.THINKING_FOR_DISCARD) {
        @Override
        public boolean canOperate(Hand hand, GamePlayer gamePlayer) {
            return gamePlayer.getHandTiles().canCallKong(hand.getActiveGamePlayer(), hand.getLastDiscardedTile().getTile());
        }
    },
    CANCEL(GamePlayerStatus.WAITING) {
        @Override
        public boolean canOperate(Hand hand, GamePlayer gamePlayer) {
            return gamePlayer.getHandTiles().canCall(hand.getActiveGamePlayer(), hand.getLastDiscardedTile().getTile());
        }
    },

    DRAW_TILE(GamePlayerStatus.THINKING_FOR_DISCARD) {
        @Override
        public boolean canOperate(Hand hand, GamePlayer gamePlayer) {
            return true;
        }
    },

    DISCARD(GamePlayerStatus.WAITING) {
        @Override
        public boolean canOperate(Hand hand, GamePlayer gamePlayer) {
            return true;
        }
    },
    CALL_FOR_CONCEALED_KONG(GamePlayerStatus.THINKING_FOR_DISCARD) {
        @Override
        public boolean canOperate(Hand hand, GamePlayer gamePlayer) {
            return gamePlayer.getHandTiles().canCallConcealedKong();
        }
    },
    CALL_FOR_ADDED_KONG(GamePlayerStatus.THINKING_FOR_DISCARD) {
        @Override
        public boolean canOperate(Hand hand, GamePlayer gamePlayer) {
            return gamePlayer.getHandTiles().canCallAddedKong();
        }
    },

    CALL_FOR_WIN(GamePlayerStatus.WON) {
        @Override
        public boolean canOperate(Hand hand, GamePlayer gamePlayer) {
            return gamePlayer.getHandTiles().canCallWin();
        }
    },
    CALL_FOR_SELF_DRAW(GamePlayerStatus.SELF_DRAWN_WIN) {
        @Override
        public boolean canOperate(Hand hand, GamePlayer gamePlayer) {
            return gamePlayer.getHandTiles().canCallSelfDraw();
        }
    };

    private final GamePlayerStatus nextStatus;

    GamePlayerOperation(GamePlayerStatus nextStatus) {
        this.nextStatus = nextStatus;
    }
}
