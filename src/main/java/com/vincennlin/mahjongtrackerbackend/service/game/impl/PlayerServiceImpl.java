package com.vincennlin.mahjongtrackerbackend.service.game.impl;

import com.vincennlin.mahjongtrackerbackend.entity.game.Player;
import com.vincennlin.mahjongtrackerbackend.entity.user.User;
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
    public PlayerDto createPlayer(CreatePlayerRequest request) {

        Player player = new Player();
        player.setType(request.getType());

        User user;

        if (request.getUserId() == null) {
            user = authService.getCurrentUser();
        } else {
            user = userService.getUserEntityByUserId(request.getUserId());
        }

        player.setUser(user);

        switch(request.getType()) {
            case HUMAN:
                break;
            case BOT:
                break;
            case AI:
                break;
        }

        Player newPlayer = playerRepository.save(player);

        return playerMapper.mapToDto(newPlayer);
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
