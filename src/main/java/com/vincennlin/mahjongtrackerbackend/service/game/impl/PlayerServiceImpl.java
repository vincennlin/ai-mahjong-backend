package com.vincennlin.mahjongtrackerbackend.service.game.impl;

import com.vincennlin.mahjongtrackerbackend.constant.game.playertype.PlayerType;
import com.vincennlin.mahjongtrackerbackend.entity.game.Player;
import com.vincennlin.mahjongtrackerbackend.entity.user.User;
import com.vincennlin.mahjongtrackerbackend.exception.ResourceNotFoundException;
import com.vincennlin.mahjongtrackerbackend.mapper.game.PlayerMapper;
import com.vincennlin.mahjongtrackerbackend.payload.game.page.PlayerPageResponse;
import com.vincennlin.mahjongtrackerbackend.payload.game.request.CreatePlayerRequest;
import com.vincennlin.mahjongtrackerbackend.payload.game.dto.PlayerDto;
import com.vincennlin.mahjongtrackerbackend.repository.game.PlayerRepository;
import com.vincennlin.mahjongtrackerbackend.service.game.PlayerService;
import com.vincennlin.mahjongtrackerbackend.service.user.AuthService;
import com.vincennlin.mahjongtrackerbackend.service.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class PlayerServiceImpl implements PlayerService {

    private final PlayerMapper playerMapper;

    private final UserService userService;
    private final AuthService authService;

    private final PlayerRepository playerRepository;

    @Override
    public PlayerPageResponse getPlayers(Pageable pageable) {

        Page<Player> pageOfPlayers = playerRepository.findAllByUserId(authService.getCurrentUser().getId(), pageable);

        return getPlayerPageResponse(pageOfPlayers);
    }

    @Override
    public Player getPlayerEntityById(Long playerId) {
        return playerRepository.findById(playerId).orElseThrow(
                () -> new ResourceNotFoundException("Player", "id", playerId));
    }

    @Override
    public Player getCurrentPlayer() {

        return playerRepository.findByUserIdAndType(
                authService.getCurrentUserId(), PlayerType.HUMAN).orElseGet(() -> {
                    Player player = new Player();
                    player.setType(PlayerType.HUMAN);
                    player.setUser(authService.getCurrentUser());
                    player.setPlayerName(player.getUser().getUsername());
                    return playerRepository.save(player);
                }
        );
    }

    @Override
    public PlayerDto createPlayer(CreatePlayerRequest request) {

        Player player = new Player();
        player.setType(request.getType());

        User user = authService.getCurrentUser();
        player.setUser(user);

        if (request.getPlayerName() != null) {
            player.setPlayerName(request.getPlayerName());
        } else {
            player.setPlayerName(getDefaultPlayerName(player));
        }

//        if (request.getType() == PlayerType.HUMAN && request.getUserId != authService.getCurrentUserId()) {
//            throw new RuntimeException("Unauthorized");
//        }
//        if (request.getUserId() == null) {
//            user = authService.getCurrentUser();
//        } else {
//            user = userService.getUserEntityByUserId(request.getUserId());
//        }

        Player newPlayer = playerRepository.save(player);

        return playerMapper.mapToDto(newPlayer);
    }

    @Override
    public PlayerDto updatePlayer(Long playerId, PlayerDto playerDto) {

        Player player = getPlayerEntityById(playerId);

        authorizeOwnershipByPlayerUserId(player.getUser().getId());

        if (playerDto.getType() != null) player.setType(playerDto.getType());

        if (playerDto.getPlayerName() != null) {
            player.setPlayerName(playerDto.getPlayerName());
        } else{
            player.setPlayerName(getDefaultPlayerName(player));
        }

        Player updatedPlayer = playerRepository.save(player);

        return playerMapper.mapToDto(updatedPlayer);
    }

    @Override
    public void deletePlayerById(Long playerId) {

        Player player = getPlayerEntityById(playerId);

        authorizeOwnershipByPlayerUserId(player.getUser().getId());

        playerRepository.deleteById(playerId);
    }

    private String getDefaultPlayerName(Player player) {
        String playerName = player.getUser().getUsername();

        if (player.getType() == PlayerType.BOT) {
            Integer botCount = playerRepository.countByUserIdAndTypeIs(player.getUser().getId(), PlayerType.BOT);
            playerName += "_BOT" + (botCount + 1);
        } else if (player.getType() == PlayerType.AI) {
            Integer aiCount = playerRepository.countByUserIdAndTypeIs(player.getUser().getId(), PlayerType.AI);
            playerName += "_AI" + (aiCount + 1);
        }

        return playerName;
    }

    private void authorizeOwnershipByPlayerUserId(Long userId) {
        if (!authService.getCurrentUser().getId().equals(userId)) {
            throw new RuntimeException("Unauthorized");
        }
    }

    private PlayerPageResponse getPlayerPageResponse(Page<Player> pageOfPlayers) {
        List<Player> listOfPlayers = pageOfPlayers.getContent();

        List<PlayerDto> playerDtoList = listOfPlayers.stream().map(playerMapper::mapToDto).toList();

        PlayerPageResponse playerPageResponse = new PlayerPageResponse();
        playerPageResponse.setContent(playerDtoList);
        playerPageResponse.setPageNo(pageOfPlayers.getNumber());
        playerPageResponse.setPageSize(pageOfPlayers.getSize());
        playerPageResponse.setTotalElements(pageOfPlayers.getTotalElements());
        playerPageResponse.setTotalPages(pageOfPlayers.getTotalPages());
        playerPageResponse.setLast(pageOfPlayers.isLast());

        return playerPageResponse;
    }
}
