package com.vincennlin.aimahjongbackend.payload.game.wind;

import lombok.Getter;

@Getter
public enum Wind implements AbstractWind{

    EAST {
        @Override
        public int getWindOrder() {
            return ordinal();
        }

        @Override
        public Wind nextWind() {
            return SOUTH;
        }

        @Override
        public String toString() {
            return "東";
        }
    },

    SOUTH {
        @Override
        public int getWindOrder() {
            return ordinal();
        }

        @Override
        public Wind nextWind() {
            return WEST;
        }

        @Override
        public String toString() {
            return "南";
        }
    },

    WEST {
        @Override
        public int getWindOrder() {
            return ordinal();
        }

        @Override
        public Wind nextWind() {
            return NORTH;
        }

        @Override
        public String toString() {
            return "西";
        }
    },

    NORTH {
        @Override
        public int getWindOrder() {
            return ordinal();
        }

        @Override
        public Wind nextWind() {
            return EAST;
        }

        @Override
        public String toString() {
            return "北";
        }
    };

    public static Wind getWind(int windOrder){
        return switch (windOrder) {
            case 0 -> EAST;
            case 1 -> SOUTH;
            case 2 -> WEST;
            case 3 -> NORTH;
            default -> throw new IllegalArgumentException("Invalid wind value: " + windOrder);
        };
    }
}
