package com.vincennlin.mahjongtrackerbackend.mapper.tile;

import com.vincennlin.mahjongtrackerbackend.entity.tile.BoardTile;
import com.vincennlin.mahjongtrackerbackend.payload.game.dto.tile.BoardTileDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

@Component
public class BoardTileMapper {

    private final ModelMapper modelMapper;

    public BoardTileMapper() {
        this.modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    public BoardTileDto mapToDto(BoardTile boardTile) {
        BoardTileDto boardTileDto = modelMapper.map(boardTile, BoardTileDto.class);
        boardTileDto.setTileName(boardTile.getTile().getName());
        return boardTileDto;
    }
}
