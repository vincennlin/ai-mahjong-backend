package com.vincennlin.aimahjongbackend.mapper.tile;

import com.vincennlin.aimahjongbackend.payload.game.dto.tile.TileDto;
import com.vincennlin.aimahjongbackend.payload.tile.impl.Tile;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TileMapper {

    private final ModelMapper modelMapper;

    public TileMapper() {
        this.modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    public TileDto mapToDto(Tile tile) {
        return new TileDto(tile.ordinal(), tile.getName());
    }

    public List<TileDto> mapTilesToDtoList(List<Tile> tiles) {
        return tiles.stream().map(this::mapToDto).toList();
    }
}
