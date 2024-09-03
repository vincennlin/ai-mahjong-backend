package com.vincennlin.mahjongtrackerbackend.service.game.impl;

import com.vincennlin.mahjongtrackerbackend.constant.game.DefaultGameConstants;
import com.vincennlin.mahjongtrackerbackend.entity.game.Game;
import com.vincennlin.mahjongtrackerbackend.entity.game.Hand;
import com.vincennlin.mahjongtrackerbackend.entity.game.Round;
import com.vincennlin.mahjongtrackerbackend.exception.GameProcessException;
import com.vincennlin.mahjongtrackerbackend.mapper.game.HandMapper;
import com.vincennlin.mahjongtrackerbackend.payload.game.dto.HandDto;
import com.vincennlin.mahjongtrackerbackend.payload.game.gamestatus.GameStatus;
import com.vincennlin.mahjongtrackerbackend.repository.game.HandRepository;
import com.vincennlin.mahjongtrackerbackend.service.game.GameService;
import com.vincennlin.mahjongtrackerbackend.service.game.HandService;
import com.vincennlin.mahjongtrackerbackend.service.game.RoundService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class HandServiceImpl implements HandService {

    private final HandMapper handMapper;

    private final GameService gameService;
    private final RoundService roundService;

    private final HandRepository handRepository;

    @Override
    public HandDto startNewHand(Long gameId) {

        Game game = gameService.getGameEntityById(gameId);

        Round round = null;

        if (game.getStatus() == GameStatus.READY_TO_START_NEW_ROUND) {
            round = roundService.startNewRoundAndGetEntity(game);
        } else if (game.getStatus() == GameStatus.READY_TO_START_NEW_HAND) {
            round = game.getCurrentRound();
        } else {
            throw new GameProcessException(HttpStatus.BAD_REQUEST, game.getStatus(), "Game is not ready to start a new hand");
        }

        Hand hand = new Hand(round, round.getNextDealer(), round.getNextPrevailingWind());

        Hand newHand = handRepository.save(hand);

        return handMapper.mapToDto(newHand, round.getRoundWind());
    }

    @Override
    public int rollDice() {
        int diceCount = DefaultGameConstants.DEFAULT_DICE_COUNT;
        return (int) ((Math.random() * 6) + 1) * diceCount;
    }
}
