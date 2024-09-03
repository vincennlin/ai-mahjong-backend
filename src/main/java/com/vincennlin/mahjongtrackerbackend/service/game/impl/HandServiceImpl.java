package com.vincennlin.mahjongtrackerbackend.service.game.impl;

import com.vincennlin.mahjongtrackerbackend.constant.game.DefaultGameConstants;
import com.vincennlin.mahjongtrackerbackend.service.game.HandService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class HandServiceImpl implements HandService {

    @Override
    public int rollDice() {
        int diceCount = DefaultGameConstants.DEFAULT_DICE_COUNT;
        return (int) ((Math.random() * 6) + 1) * diceCount;
    }
}
