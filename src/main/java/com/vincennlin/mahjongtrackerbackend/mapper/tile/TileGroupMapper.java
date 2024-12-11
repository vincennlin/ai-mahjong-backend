package com.vincennlin.mahjongtrackerbackend.mapper.tile;

import com.vincennlin.mahjongtrackerbackend.entity.tile.BoardTile;
import com.vincennlin.mahjongtrackerbackend.entity.tile.PlayerTile;
import com.vincennlin.mahjongtrackerbackend.entity.tile.tilegroup.ExposedTileGroup;
import com.vincennlin.mahjongtrackerbackend.entity.tile.tilegroup.PlayerTileGroup;
import com.vincennlin.mahjongtrackerbackend.entity.tile.tilegroup.WallTileGroup;
import com.vincennlin.mahjongtrackerbackend.payload.game.dto.tile.*;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TileGroupMapper {

    private final ModelMapper modelMapper;
    private final BoardTileMapper boardTileMapper;

    public TileGroupMapper() {
        this.modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        this.boardTileMapper = new BoardTileMapper();

        Converter<BoardTile, BoardTileDto> boardTileConverter = context ->
                context.getSource() == null ? null : modelMapper.map(context.getSource(), BoardTileDto.class);
        modelMapper.addConverter(boardTileConverter);
    }

    public WallTileGroupDto mapWallTileGroupToDto(WallTileGroup wallTileGroup) {
        WallTileGroupDto wallTileGroupDto = modelMapper.map(wallTileGroup, WallTileGroupDto.class);
        wallTileGroupDto.setHandId(wallTileGroup.getHand().getId());
        wallTileGroupDto.setTileCount(wallTileGroup.getTiles().size());
        wallTileGroupDto.setTiles(boardTileMapper.mapBoardTilesToDto(wallTileGroup.getTiles()));
        return wallTileGroupDto;
    }

    public List<ExposedTileGroupDto> mapExposedTileGroupListToStringDto(List<ExposedTileGroup> exposedTileGroups) {
        if (exposedTileGroups != null) {
            return exposedTileGroups.stream()
                    .map(this::mapExposedTileGroupsToStringDto)
                    .toList();
        }
        return null;
    }

    public ExposedTileGroupDto mapExposedTileGroupsToStringDto(ExposedTileGroup exposedTileGroup) {
        ExposedTileGroupDto exposedTileGroupDto = modelMapper.map(exposedTileGroup, ExposedTileGroupDto.class);
        String[] tilesString = exposedTileGroup.convertTilesToString();
        exposedTileGroupDto.setTilesNum(tilesString[0]);
        exposedTileGroupDto.setTilesSub(tilesString[1]);
        exposedTileGroupDto.setTileCount(exposedTileGroup.getTiles().size());

        exposedTileGroupDto.setTiles(boardTileMapper.mapBoardTilesToDto(exposedTileGroup.getTiles()));

        exposedTileGroupDto.setPlayerId(exposedTileGroup.getPlayerId());

        exposedTileGroupDto.setMeldType(exposedTileGroup.getMeldType());

        return exposedTileGroupDto;
    }

    public PlayerTileGroupDto mapPlayerTileGroupToStringDto(PlayerTileGroup playerTileGroup) {
        PlayerTileGroupDto playerTileGroupDto = modelMapper.map(playerTileGroup, PlayerTileGroupDto.class);
        String[] tilesString = playerTileGroup.convertTilesToString();
        playerTileGroupDto.setTilesNum(tilesString[0]);
        playerTileGroupDto.setTilesSub(tilesString[1]);
        playerTileGroupDto.setTileCount(playerTileGroup.getTiles().size());

//        playerTileGroupDto.setTiles(null);
        playerTileGroupDto.setTiles(boardTileMapper.mapBoardTilesToDto(playerTileGroup.getTiles()));

        playerTileGroupDto.setPlayerId(playerTileGroup.getPlayerId());
        return playerTileGroupDto;
    }

    public PlayerTileGroupDto mapPlayerTileGroupToDto(PlayerTileGroup playerTileGroup) {
        PlayerTileGroupDto playerTileGroupDto = modelMapper.map(playerTileGroup, PlayerTileGroupDto.class);
        playerTileGroupDto.setTiles(boardTileMapper.mapBoardTilesToDto(playerTileGroup.getTiles()));
        playerTileGroupDto.setTileCount(playerTileGroupDto.getTiles().size());
        playerTileGroupDto.setPlayerId(playerTileGroup.getPlayerId());
        return playerTileGroupDto;
    }

    public PlayerTileDto mapPlayerTileToDto(PlayerTile playerTile) {
        PlayerTileDto playerTileDto = modelMapper.map(playerTile, PlayerTileDto.class);
        playerTileDto.setHandId(playerTile.getHand().getId());
        playerTileDto.setGamePlayerId(playerTile.getGamePlayer().getId());

//        playerTileDto.setHandTiles(mapPlayerTileGroupToDto(playerTile.getHandTiles()));
//        playerTileDto.setExposedTiles(mapPlayerTileGroupToDto(playerTile.getExposedTiles()));
//        playerTileDto.setDiscardedTiles(mapPlayerTileGroupToDto(playerTile.getDiscardedTiles()));

        playerTileDto.setHandTiles(mapPlayerTileGroupToStringDto(playerTile.getHandTiles()));
        playerTileDto.setFlowerTiles(mapPlayerTileGroupToStringDto(playerTile.getFlowerTiles()));
        playerTileDto.setExposedTileList(mapExposedTileGroupListToStringDto(playerTile.getExposedTiles()));
        playerTileDto.setDiscardedTiles(mapPlayerTileGroupToStringDto(playerTile.getDiscardedTiles()));
        return playerTileDto;
    }
}
