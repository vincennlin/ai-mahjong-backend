package com.vincennlin.mahjongtrackerbackend.mapper.game;

import com.vincennlin.mahjongtrackerbackend.entity.game.GamePlayer;
import com.vincennlin.mahjongtrackerbackend.entity.game.Hand;
import com.vincennlin.mahjongtrackerbackend.entity.tile.PlayerTile;
import com.vincennlin.mahjongtrackerbackend.mapper.tile.BoardTileMapper;
import com.vincennlin.mahjongtrackerbackend.mapper.tile.TileGroupMapper;
import com.vincennlin.mahjongtrackerbackend.payload.game.dto.BoardDto;
import com.vincennlin.mahjongtrackerbackend.payload.game.dto.PlayerViewDto;
import com.vincennlin.mahjongtrackerbackend.payload.game.status.HandStatus;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

@Component
public class BoardMapper {

    private final ModelMapper modelMapper;
    private final TileGroupMapper tileGroupMapper;
    private final BoardTileMapper boardTileMapper;

    public BoardMapper() {
        this.modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        this.tileGroupMapper = new TileGroupMapper();
        this.boardTileMapper = new BoardTileMapper();
    }

    public BoardDto mapToDto(Hand hand) {
        BoardDto boardDto = new BoardDto();
        boardDto.setGameId(hand.getGame().getId());
        boardDto.setHandId(hand.getId());
        boardDto.setActiveGamePlayerId(hand.getActiveGamePlayer().getId());
        if (hand.getLastDiscardedTile() != null) {
            boardDto.setLastDiscardedTile(boardTileMapper.mapToDto(hand.getLastDiscardedTile()));
        }
        boardDto.setStatus(hand.getStatus());
        boardDto.setAcceptableOperations(hand.getStatus().getAcceptableOperations());
        boardDto.setWallTiles(tileGroupMapper.mapWallTileGroupToDto(hand.getWallTileGroup()));
        if (hand.getPlayerTiles() != null) {
            for (PlayerTile playerTile : hand.getPlayerTiles()) {
                if (hand.getStatus() != HandStatus.FINISHED_DEALING && hand.getStatus() != HandStatus.FINISHED_BREAKING_WALL) {
                    playerTile.getHandTiles().sortHandTiles();
                }
                boardDto.getPlayerTiles().add(tileGroupMapper.mapPlayerTileToDto(playerTile));
            }
        }
        boardDto.setRoundWind(hand.getRound().getRoundWind());
        boardDto.setPrevailingWind(hand.getPrevailingWind());
        return boardDto;
    }

    public PlayerViewDto mapToPlayerViewDto(Hand hand, GamePlayer gamePlayer) {
        PlayerViewDto playerViewDto = new PlayerViewDto();
        playerViewDto.setGameId(hand.getGame().getId());
        playerViewDto.setHandId(hand.getId());
        playerViewDto.setActiveGamePlayerId(hand.getActiveGamePlayer().getId());
        if (hand.getLastDiscardedTile() != null) {
            playerViewDto.setLastDiscardedTile(boardTileMapper.mapToDto(hand.getLastDiscardedTile()));
        }
        playerViewDto.setHandStatus(hand.getStatus());
        playerViewDto.setGamePlayerStatus(gamePlayer.getStatus());
        playerViewDto.setAcceptableOperations(gamePlayer.getPlayerTile().getHandTiles().getAcceptableOperations());
        playerViewDto.setRoundWind(hand.getRound().getRoundWind());
        playerViewDto.setPrevailingWind(hand.getPrevailingWind());

        playerViewDto.setWallTiles(tileGroupMapper.mapWallTileGroupToDto(hand.getWallTileGroup()));

        if (hand.getPlayerTiles() != null) {
            for (PlayerTile playerTile : hand.getPlayerTiles()) {
                if (hand.getStatus() != HandStatus.FINISHED_DEALING && hand.getStatus() != HandStatus.FINISHED_BREAKING_WALL) {
                    playerTile.getHandTiles().sortHandTiles();
                }
            }
        }

        playerViewDto.setPlayerTile(tileGroupMapper.mapPlayerTileToDto(hand.getPlayerTileByGamePlayer(gamePlayer)));

        playerViewDto.setDownwindPlayerTile(tileGroupMapper.mapPlayerTileToDto(hand.getPlayerTileByGamePlayer(gamePlayer.getDownwindPlayer())));
        playerViewDto.setOppositePlayerTile(tileGroupMapper.mapPlayerTileToDto(hand.getPlayerTileByGamePlayer(gamePlayer.getDownwindPlayer().getDownwindPlayer())));
        playerViewDto.setUpwindPlayerTile(tileGroupMapper.mapPlayerTileToDto(hand.getPlayerTileByGamePlayer(gamePlayer.getDownwindPlayer().getDownwindPlayer().getDownwindPlayer())));

        playerViewDto.hideTiles();

        return playerViewDto;
    }
}
