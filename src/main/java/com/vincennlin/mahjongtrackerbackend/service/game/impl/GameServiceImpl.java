package com.vincennlin.mahjongtrackerbackend.service.game.impl;

import com.vincennlin.mahjongtrackerbackend.entity.game.Game;
import com.vincennlin.mahjongtrackerbackend.exception.ResourceNotFoundException;
import com.vincennlin.mahjongtrackerbackend.exception.ResourceOwnershipException;
import com.vincennlin.mahjongtrackerbackend.mapper.game.GameMapper;
import com.vincennlin.mahjongtrackerbackend.payload.game.page.GamePageResponse;
import com.vincennlin.mahjongtrackerbackend.payload.game.request.CreateGameRequest;
import com.vincennlin.mahjongtrackerbackend.payload.game.dto.GameDto;
import com.vincennlin.mahjongtrackerbackend.repository.game.GameRepository;
import com.vincennlin.mahjongtrackerbackend.service.game.GameService;
import com.vincennlin.mahjongtrackerbackend.service.user.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class GameServiceImpl implements GameService {

    private final GameMapper gameMapper;

    private final AuthService authService;

    private final GameRepository gameRepository;

    @Override
    public GamePageResponse getGames(Pageable pageable) {

        Page<Game> pageOfGames = gameRepository.findAllByCreatorId(authService.getCurrentUser().getId(), pageable);

        return getGamePageResponse(pageOfGames);
    }

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

    @Override
    public GameDto updateGame(Long gameId, GameDto gameDto) {

        Game game = getGameEntityById(gameId);

        authorizeOwnershipByGameCreatorId(game.getCreator().getId());

        if (gameDto.getBasePoint() != null) game.setBasePoint(gameDto.getBasePoint());
        if (gameDto.getFannPoint() != null) game.setFannPoint(gameDto.getFannPoint());
        if (gameDto.getDollarPerPoint() != null) game.setDollarPerPoint(gameDto.getDollarPerPoint());

        Game updatedGame = gameRepository.save(game);

        return gameMapper.mapToDto(updatedGame);
    }

    @Override
    public void deleteGameById(Long gameId) {

        Game game = getGameEntityById(gameId);

        authorizeOwnershipByGameCreatorId(game.getCreator().getId());

        gameRepository.deleteById(gameId);
    }

    private void authorizeOwnershipByGameCreatorId(Long gameCreatorId) {
        if (gameCreatorId != authService.getCurrentUserId()) {
            throw new ResourceOwnershipException(authService.getCurrentUserId());
        }
    }

    private Game getGameEntityById(Long gameId) {
        return gameRepository.findById(gameId).orElseThrow(
                () -> new ResourceNotFoundException("Game", "id", gameId));
    }

    private GamePageResponse getGamePageResponse(Page<Game> pageOfGames) {
        List<Game> listOfGames = pageOfGames.getContent();

        List<GameDto> gameDtoList = listOfGames.stream().map(gameMapper::mapToDto).toList();

        GamePageResponse gamePageResponse = new GamePageResponse();
        gamePageResponse.setContent(gameDtoList);
        gamePageResponse.setPageNo(pageOfGames.getNumber());
        gamePageResponse.setPageSize(pageOfGames.getSize());
        gamePageResponse.setTotalElements(pageOfGames.getTotalElements());
        gamePageResponse.setTotalPages(pageOfGames.getTotalPages());
        gamePageResponse.setLast(pageOfGames.isLast());

        return gamePageResponse;
    }
}
