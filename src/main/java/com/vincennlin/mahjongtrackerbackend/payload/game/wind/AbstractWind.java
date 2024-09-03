package com.vincennlin.mahjongtrackerbackend.payload.game.wind;

public interface AbstractWind {

    int getWindOrder();

    Wind nextWind();
}
