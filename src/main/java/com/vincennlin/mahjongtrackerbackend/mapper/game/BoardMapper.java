package com.vincennlin.mahjongtrackerbackend.mapper.game;

import com.vincennlin.mahjongtrackerbackend.entity.game.GamePlayer;
import com.vincennlin.mahjongtrackerbackend.entity.game.Hand;
import com.vincennlin.mahjongtrackerbackend.entity.tile.PlayerTile;
import com.vincennlin.mahjongtrackerbackend.mapper.tile.TileGroupMapper;
import com.vincennlin.mahjongtrackerbackend.payload.game.dto.BoardDto;
import com.vincennlin.mahjongtrackerbackend.payload.game.dto.PlayerViewDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BoardMapper {

    private final ModelMapper modelMapper;
    private final TileGroupMapper tileGroupMapper;

    public BoardMapper() {
        this.modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        this.tileGroupMapper = new TileGroupMapper();
    }

    public BoardDto mapToDto(Hand hand) {
        BoardDto boardDto = new BoardDto();
        boardDto.setHandId(hand.getId());
        boardDto.setWallTiles(tileGroupMapper.mapWallTileGroupToDto(hand.getWallTileGroup()));
        if (hand.getPlayerTiles() != null) {
            for (PlayerTile playerTile : hand.getPlayerTiles()) {
                boardDto.getPlayerTiles().add(tileGroupMapper.mapPlayerTileToDto(playerTile));
            }
        }
        boardDto.setRoundWind(hand.getRound().getRoundWind());
        boardDto.setPrevailingWind(hand.getPrevailingWind());
        return boardDto;
    }

    public PlayerViewDto mapToPlayerViewDto(Hand hand, GamePlayer gamePlayer) {
        PlayerViewDto playerViewDto = new PlayerViewDto();
        playerViewDto.setHandId(hand.getId());
        playerViewDto.setRoundWind(hand.getRound().getRoundWind());
        playerViewDto.setPrevailingWind(hand.getPrevailingWind());

        playerViewDto.setWallTiles(tileGroupMapper.mapWallTileGroupToDto(hand.getWallTileGroup()));
        playerViewDto.getWallTiles().setTiles(null);

        playerViewDto.setPlayerTile(tileGroupMapper.mapPlayerTileToDto(hand.getPlayerTileByGamePlayer(gamePlayer)));

        playerViewDto.setDownwindPlayerTile(tileGroupMapper.mapPlayerTileToDto(hand.getPlayerTileByGamePlayer(gamePlayer.getDownwindPlayer())));
        playerViewDto.getDownwindPlayerTile().getHandTiles().setTiles(null);
        playerViewDto.setOppositePlayerTile(tileGroupMapper.mapPlayerTileToDto(hand.getPlayerTileByGamePlayer(gamePlayer.getDownwindPlayer().getDownwindPlayer())));
        playerViewDto.getOppositePlayerTile().getHandTiles().setTiles(null);
        playerViewDto.setUpwindPlayerTile(tileGroupMapper.mapPlayerTileToDto(hand.getPlayerTileByGamePlayer(gamePlayer.getDownwindPlayer().getDownwindPlayer().getDownwindPlayer())));
        playerViewDto.getUpwindPlayerTile().getHandTiles().setTiles(null);

        return playerViewDto;
    }
}
