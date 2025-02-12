package com.vincennlin.aimahjongbackend.payload.game.wind;

public interface AbstractWind {

    int getWindOrder();

    Wind nextWind();
}
