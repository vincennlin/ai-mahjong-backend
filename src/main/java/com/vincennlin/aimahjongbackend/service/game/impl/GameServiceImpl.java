package com.vincennlin.aimahjongbackend.service.game.impl;

import com.vincennlin.aimahjongbackend.constant.game.DefaultGameConstants;
import com.vincennlin.aimahjongbackend.exception.ProcessException;
import com.vincennlin.aimahjongbackend.payload.game.status.GameStatus;
import com.vincennlin.aimahjongbackend.entity.game.Game;
import com.vincennlin.aimahjongbackend.entity.game.GamePlayer;
import com.vincennlin.aimahjongbackend.entity.game.Player;
import com.vincennlin.aimahjongbackend.exception.ResourceNotFoundException;
import com.vincennlin.aimahjongbackend.exception.ResourceOwnershipException;
import com.vincennlin.aimahjongbackend.exception.WebAPIException;
import com.vincennlin.aimahjongbackend.mapper.game.GameMapper;
import com.vincennlin.aimahjongbackend.payload.game.page.GamePageResponse;
import com.vincennlin.aimahjongbackend.payload.game.request.game.CreateGameRequest;
import com.vincennlin.aimahjongbackend.payload.game.dto.GameDto;
import com.vincennlin.aimahjongbackend.repository.game.GameRepository;
import com.vincennlin.aimahjongbackend.service.game.GamePlayerService;
import com.vincennlin.aimahjongbackend.service.game.GameService;
import com.vincennlin.aimahjongbackend.service.game.PlayerService;
import com.vincennlin.aimahjongbackend.service.user.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@AllArgsConstructor
@Service
public class GameServiceImpl implements GameService {

    private final GameMapper gameMapper;

    private final GamePlayerService gamePlayerService;
    private final PlayerService playerService;
    private final AuthService authService;

    private final GameRepository gameRepository;

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

        Game game = gameRepository.findById(gameId).orElseThrow(
                () -> new ResourceNotFoundException("Game", "id", gameId));

        if (!game.containsUserById(authService.getCurrentUserId())) {
            throw new ResourceOwnershipException(authService.getCurrentUserId());
        }

        return game;
    }

    @Transactional
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

        game = gameRepository.save(game);

        addPlayerToGame(game, currentPlayer);

        Game newGame = gameRepository.save(game);

        return gameMapper.mapToDto(newGame);
    }

    @Transactional
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
    public GameDto saveGame(Game game) {
        return gameMapper.mapToDto(gameRepository.save(game));
    }

    @Transactional
    @Override
    public void deleteGameById(Long gameId) {

        Game game = getGameEntityById(gameId);

        authorizeOwnershipByGameCreatorId(game.getCreator().getId());

        gameRepository.deleteById(gameId);
    }

    @Transactional
    @Override
    public GameDto addPlayerToGame(Long gameId, Long playerId) {

        Game game = getGameEntityById(gameId);

        if (game.getGamePlayers().size() >= 4) {
            throw new WebAPIException(HttpStatus.BAD_REQUEST, "Game is full, cannot add more players");
        }
        if (game.containsPlayerById(playerId)) {
            throw new WebAPIException(HttpStatus.BAD_REQUEST, "Player is already in the game");
        }

        Player player = playerService.getPlayerEntityById(playerId);

        addPlayerToGame(game, player);

        if (game.getGamePlayers().size() == DefaultGameConstants.DEFAULT_PLAYER_COUNT) {
            game.setStatus(GameStatus.READY_TO_START);
        }

        return gameMapper.mapToDto(getGameEntityById(gameId));
    }

    private void addPlayerToGame(Game game, Player player) {

        GamePlayer newGamePlayer = gamePlayerService.createGamePlayerAndGetEntity(game, player);

        game.getGamePlayers().add(newGamePlayer);
    }

    @Transactional
    @Override
    public GameDto removePlayerFromGame(Long gameId, Long playerId) {

        Game game = getGameEntityById(gameId);

        if (game.getGamePlayers().size() <= 1) {
            throw new WebAPIException(HttpStatus.BAD_REQUEST, "Game only has one player, cannot remove player");
        }
        if (!game.containsPlayerById(playerId)) {
            throw new WebAPIException(HttpStatus.BAD_REQUEST, "Player is not in the game");
        }

        GamePlayer gamePlayer = gamePlayerService.getGamePlayerEntityByPlayerId(playerId);

        game.getGamePlayers().remove(gamePlayer);
        gamePlayerService.deleteGamePlayerById(gamePlayer.getId());

        if (game.getGamePlayers().size() < 4) {
            game.setStatus(GameStatus.WAITING_FOR_PLAYERS);
        }

        Game savedGame = gameRepository.save(game);

        return gameMapper.mapToDto(savedGame);
    }

    @Override
    public GameDto pickSeats(Long gameId) {

        Game game = getGameEntityById(gameId);

        if (game.getStatus() != GameStatus.READY_TO_START) {
            throw new ProcessException(HttpStatus.BAD_REQUEST, game.getStatus(), "Game is not in ready state");
        }

        List<GamePlayer> gamePlayerList = game.getGamePlayers();
        Collections.shuffle(gamePlayerList);

        int playerCount = gamePlayerList.size();
        for (int i = 0; i < playerCount; i++) {
            GamePlayer gamePlayer = gamePlayerList.get(i);
            gamePlayer.setDownwindPlayer(gamePlayerList.get((i + 1) % playerCount));
        }

        game.setEastPlayer(gamePlayerList.get(0));

        game.setStatus(GameStatus.FINISHED_PICKING_SEATS);

        Game savedGame = gameRepository.save(game);

        return gameMapper.mapToDto(savedGame);
    }

    @Override
    public GameDto decideEastPlayer(Long gameId) {

        Game game = getGameEntityById(gameId);

        if (game.getStatus() != GameStatus.FINISHED_PICKING_SEATS) {
            throw new ProcessException(HttpStatus.BAD_REQUEST, game.getStatus(), "Game is not in finished picking seats state");
        }

        int randomIndex = (int) (Math.random() * 4);
        game.setEastPlayer(game.getGamePlayers().get(randomIndex));

        GamePlayer currentPlayer = game.getEastPlayer();

        for (int i = 0; i < DefaultGameConstants.DEFAULT_PLAYER_COUNT; i++) {
            game.getGamePlayers().set(i, currentPlayer);
            currentPlayer = currentPlayer.getDownwindPlayer();
        }

        game.setStatus(GameStatus.READY_TO_START_NEW_ROUND);

        Game savedGame = gameRepository.save(game);

        return gameMapper.mapToDto(savedGame);
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
