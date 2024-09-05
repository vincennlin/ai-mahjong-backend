package com.vincennlin.mahjongtrackerbackend.mapper.tile;

import com.vincennlin.mahjongtrackerbackend.entity.tile.BoardTile;
import com.vincennlin.mahjongtrackerbackend.entity.tile.PlayerTile;
import com.vincennlin.mahjongtrackerbackend.entity.tile.tilegroup.HandTileGroup;
import com.vincennlin.mahjongtrackerbackend.entity.tile.tilegroup.PlayerTileGroup;
import com.vincennlin.mahjongtrackerbackend.entity.tile.tilegroup.WallTileGroup;
import com.vincennlin.mahjongtrackerbackend.payload.game.dto.tile.BoardTileDto;
import com.vincennlin.mahjongtrackerbackend.payload.game.dto.tile.PlayerTileDto;
import com.vincennlin.mahjongtrackerbackend.payload.game.dto.tile.PlayerTileGroupDto;
import com.vincennlin.mahjongtrackerbackend.payload.game.dto.tile.WallTileGroupDto;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

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
        wallTileGroupDto.setTiles(boardTileMapper.mapBoardTilesToDto(wallTileGroup.getTiles()));
        return wallTileGroupDto;
    }

    public PlayerTileGroupDto mapPlayerTileGroupToDto(PlayerTileGroup playerTileGroup) {
        PlayerTileGroupDto playerTileGroupDto = modelMapper.map(playerTileGroup, PlayerTileGroupDto.class);
        playerTileGroupDto.setTiles(boardTileMapper.mapBoardTilesToDto(playerTileGroup.getTiles()));
        playerTileGroupDto.setPlayerId(playerTileGroup.getPlayerId());
        return playerTileGroupDto;
    }

    public PlayerTileDto mapPlayerTileToDto(PlayerTile playerTile) {
        PlayerTileDto playerTileDto = modelMapper.map(playerTile, PlayerTileDto.class);
        playerTileDto.setHandId(playerTile.getHand().getId());
        playerTileDto.setGamePlayerId(playerTile.getGamePlayer().getId());
        playerTileDto.setHandTiles(mapPlayerTileGroupToDto(playerTile.getHandTiles()));
        playerTileDto.setExposedTiles(mapPlayerTileGroupToDto(playerTile.getExposedTiles()));
        playerTileDto.setDiscardedTiles(mapPlayerTileGroupToDto(playerTile.getDiscardedTiles()));
        return playerTileDto;
    }
}
