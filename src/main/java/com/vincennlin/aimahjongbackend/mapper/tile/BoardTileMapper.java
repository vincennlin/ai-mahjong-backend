package com.vincennlin.aimahjongbackend.mapper.tile;

import com.vincennlin.aimahjongbackend.entity.tile.BoardTile;
import com.vincennlin.aimahjongbackend.entity.tile.tilegroup.ExposedTileGroup;
import com.vincennlin.aimahjongbackend.payload.game.dto.tile.BoardTileDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BoardTileMapper {

    private final ModelMapper modelMapper;

    public BoardTileMapper() {
        this.modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    public BoardTileDto mapToDto(BoardTile boardTile) {
        return new BoardTileDto(boardTile.getId(), boardTile.getTile().ordinal(), boardTile.getTile().getName());
    }

    public List<BoardTileDto> mapBoardTilesToDto(List<BoardTile> boardTiles) {
        return boardTiles.stream().map(this::mapToDto).toList();
    }

    public List<BoardTileDto> mapExposedBoardTilesToDto(ExposedTileGroup exposedTileGroup) {
        List<BoardTileDto> boardTileList = new ArrayList<>();

        boardTileList.add(mapToDto(exposedTileGroup.getLeftBoardTile()));
        boardTileList.add(mapToDto(exposedTileGroup.getMiddleBoardTile()));
        boardTileList.add(mapToDto(exposedTileGroup.getRightBoardTile()));
        if (exposedTileGroup.getTopBoardTile() != null) {
            boardTileList.add(mapToDto(exposedTileGroup.getTopBoardTile()));
        }

        return boardTileList;
    }
}
