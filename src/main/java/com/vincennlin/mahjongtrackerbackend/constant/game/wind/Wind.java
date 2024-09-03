package com.vincennlin.mahjongtrackerbackend.constant.game.wind;

import lombok.Getter;

@Getter
public enum Wind implements AbstractWind{

    EAST{
        @Override
        public int getWindValue() {
            return 0;
        }

        @Override
        public String getWindString() {
            return "東";
        }
    },

    SOUTH{
        @Override
        public int getWindValue() {
            return 1;
        }

        @Override
        public String getWindString() {
            return "南";
        }
    },

    WEST{
        @Override
        public int getWindValue() {
            return 2;
        }

        @Override
        public String getWindString() {
            return "西";
        }
    },

    NORTH{
        @Override
        public int getWindValue() {
            return 3;
        }

        @Override
        public String getWindString() {
            return "北";
        }
    };


}
