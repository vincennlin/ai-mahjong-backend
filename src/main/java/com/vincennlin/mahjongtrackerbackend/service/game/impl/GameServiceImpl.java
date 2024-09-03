package com.vincennlin.mahjongtrackerbackend.service.game.impl;

import com.vincennlin.mahjongtrackerbackend.entity.game.Game;
import com.vincennlin.mahjongtrackerbackend.entity.game.GamePlayer;
import com.vincennlin.mahjongtrackerbackend.entity.game.Player;
import com.vincennlin.mahjongtrackerbackend.exception.ResourceNotFoundException;
import com.vincennlin.mahjongtrackerbackend.exception.ResourceOwnershipException;
import com.vincennlin.mahjongtrackerbackend.mapper.game.GameMapper;
import com.vincennlin.mahjongtrackerbackend.payload.game.page.GamePageResponse;
import com.vincennlin.mahjongtrackerbackend.payload.game.request.CreateGameRequest;
import com.vincennlin.mahjongtrackerbackend.payload.game.dto.GameDto;
import com.vincennlin.mahjongtrackerbackend.repository.game.GamePlayerRepository;
import com.vincennlin.mahjongtrackerbackend.repository.game.GameRepository;
import com.vincennlin.mahjongtrackerbackend.service.game.GameService;
import com.vincennlin.mahjongtrackerbackend.service.game.PlayerService;
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

    private final PlayerService playerService;
    private final AuthService authService;

    private final GameRepository gameRepository;
    private final GamePlayerRepository gamePlayerRepository;

    @Override
    public GamePageResponse getGames(Pageable pageable) {

        Page<Game> pageOfGames = gameRepository.findAllByCreatorId(authService.getCurrentUser().getId(), pageable);

        return getGamePageResponse(pageOfGames);
    }

    @Override
    public GameDto getGameById(Long gameId) {

        return gameMapper.mapToDto(getGameEntityById(gameId));
    }

    @Override
    public Game getGameEntityById(Long gameId) {
        return gameRepository.findById(gameId).orElseThrow(
                () -> new ResourceNotFoundException("Game", "id", gameId));
    }

    @Override
    public GameDto createGame(CreateGameRequest request) {

        Game game = new Game(authService.getCurrentUser());

        Player currentPlayer = playerService.getCurrentPlayer();

        if (request.getBasePoint() != null) {
            game.setBasePoint(request.getBasePoint());
        }
        if (request.getFannPoint() != null) {
            game.setFannPoint(request.getFannPoint());
        }
        if (request.getDollarPerPoint() != null) {
            game.setDollarPerPoint(request.getDollarPerPoint());
        }

        addPlayerToGame(game, currentPlayer);

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

    @Override
    public GameDto addPlayerToGame(Long gameId, Long playerId) {

        Player player = playerService.getPlayerEntityById(playerId);

        addPlayerToGame(getGameEntityById(gameId), player);

        return gameMapper.mapToDto(getGameEntityById(gameId));
    }

    private void addPlayerToGame(Game game, Player player) {

        GamePlayer gamePlayer = new GamePlayer();
        gamePlayer.setGame(game);
        gamePlayer.setPlayer(player);

        gameRepository.save(game);

        GamePlayer newGamePlayer = gamePlayerRepository.save(gamePlayer);

        game.getGamePlayers().add(newGamePlayer);
    }

    private void authorizeOwnershipByGameCreatorId(Long gameCreatorId) {
        if (gameCreatorId != authService.getCurrentUserId()) {
            throw new ResourceOwnershipException(authService.getCurrentUserId());
        }
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
