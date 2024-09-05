package com.vincennlin.mahjongtrackerbackend.service.game.impl;

import com.vincennlin.mahjongtrackerbackend.constant.game.DefaultGameConstants;
import com.vincennlin.mahjongtrackerbackend.entity.game.Hand;
import com.vincennlin.mahjongtrackerbackend.entity.tile.BoardTile;
import com.vincennlin.mahjongtrackerbackend.entity.tile.tilegroup.WallTileGroup;
import com.vincennlin.mahjongtrackerbackend.exception.InternalGameError;
import com.vincennlin.mahjongtrackerbackend.mapper.tile.BoardTileMapper;
import com.vincennlin.mahjongtrackerbackend.payload.tile.impl.Tile;
import com.vincennlin.mahjongtrackerbackend.repository.game.BoardTileRepository;
import com.vincennlin.mahjongtrackerbackend.repository.game.WallTileGroupRepository;
import com.vincennlin.mahjongtrackerbackend.service.game.TileService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@AllArgsConstructor
@Service
public class TileServiceImpl implements TileService {

    private final WallTileGroupRepository wallTileGroupRepository;
    private final BoardTileRepository boardTileRepository;

    @Override
    public WallTileGroup createWallTileGroup(Hand hand) {

        WallTileGroup wallTileGroup = wallTileGroupRepository.save(new WallTileGroup(hand));

        List<BoardTile> boardTiles = new ArrayList<>();

        for (Tile tile : Tile.values()) {
            for (int i = 0; i < tile.getTileType().getQuantity(); i++) {
                BoardTile boardTile = new BoardTile(tile, wallTileGroup);
                boardTiles.add(boardTile);
            }
        }

        if (boardTiles.size() != DefaultGameConstants.DEFAULT_TILE_COUNT) {
            throw new InternalGameError(HttpStatus.INTERNAL_SERVER_ERROR, "Board tiles count is not correct");
        }

        Collections.shuffle(boardTiles);

        List<BoardTile> savedBoardTiles = saveBoardTiles(boardTiles);

        wallTileGroup.setTiles(savedBoardTiles);

        return wallTileGroup;
    }

    @Override
    public List<BoardTile> saveBoardTiles(List<BoardTile> boardTiles) {

        return boardTileRepository.saveAll(boardTiles);
    }
}
