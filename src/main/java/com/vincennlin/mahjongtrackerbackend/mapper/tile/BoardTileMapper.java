package com.vincennlin.mahjongtrackerbackend.mapper.tile;

import com.vincennlin.mahjongtrackerbackend.entity.tile.BoardTile;
import com.vincennlin.mahjongtrackerbackend.payload.game.dto.tile.BoardTileDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BoardTileMapper {

    private final ModelMapper modelMapper;

    public BoardTileMapper() {
        this.modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    public BoardTileDto mapToDto(BoardTile boardTile) {
        return new BoardTileDto(boardTile.getTile().getName());
    }

    public List<BoardTileDto> mapBoardTilesToDto(List<BoardTile> boardTiles) {
        return boardTiles.stream().map(this::mapToDto).toList();
    }
}
