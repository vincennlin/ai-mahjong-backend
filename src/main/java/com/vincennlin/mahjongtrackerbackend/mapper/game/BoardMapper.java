package com.vincennlin.mahjongtrackerbackend.mapper.game;

import com.vincennlin.mahjongtrackerbackend.entity.game.Hand;
import com.vincennlin.mahjongtrackerbackend.entity.tile.PlayerTile;
import com.vincennlin.mahjongtrackerbackend.mapper.tile.TileGroupMapper;
import com.vincennlin.mahjongtrackerbackend.payload.game.dto.BoardDto;
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

    public BoardDto mapToDto(Hand hand, List<PlayerTile> playerTileList) {
        BoardDto boardDto = new BoardDto();
        boardDto.setHandId(hand.getId());
        boardDto.setWallTiles(tileGroupMapper.mapWallTileGroupToDto(hand.getWallTileGroup()));
        for (PlayerTile playerTile : playerTileList) {
            boardDto.getPlayerTiles().add(tileGroupMapper.mapPlayerTileToDto(playerTile));
        }
        boardDto.setRoundWind(hand.getRound().getRoundWind());
        boardDto.setPrevailingWind(hand.getPrevailingWind());
        return boardDto;
    }
}
