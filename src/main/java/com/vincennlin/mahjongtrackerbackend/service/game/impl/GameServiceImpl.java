package com.vincennlin.mahjongtrackerbackend.service.game.impl;

import com.vincennlin.mahjongtrackerbackend.constant.gamestatus.GameStatus;
import com.vincennlin.mahjongtrackerbackend.entity.game.Game;
import com.vincennlin.mahjongtrackerbackend.entity.user.User;
import com.vincennlin.mahjongtrackerbackend.mapper.GameMapper;
import com.vincennlin.mahjongtrackerbackend.payload.game.CreateGameRequest;
import com.vincennlin.mahjongtrackerbackend.payload.game.GameDto;
import com.vincennlin.mahjongtrackerbackend.repository.game.GameRepository;
import com.vincennlin.mahjongtrackerbackend.service.game.GameService;
import com.vincennlin.mahjongtrackerbackend.service.user.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class GameServiceImpl implements GameService {

    private final GameMapper gameMapper;

    private final AuthService authService;

    private final GameRepository gameRepository;

    @Override
    public GameDto createGame(CreateGameRequest request) {

        Game game = new Game(authService.getCurrentUser());

        if (request.getBasePoint() != null) {
            game.setBasePoint(request.getBasePoint());
        }
        if (request.getFannPoint() != null) {
            game.setFannPoint(request.getFannPoint());
        }
        if (request.getDollarPerPoint() != null) {
            game.setDollarPerPoint(request.getDollarPerPoint());
        }

        Game newGame = gameRepository.save(game);

        return gameMapper.mapToDto(newGame);
    }
}
