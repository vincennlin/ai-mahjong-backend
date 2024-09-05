package com.vincennlin.mahjongtrackerbackend.service.game.impl;

import com.vincennlin.mahjongtrackerbackend.mapper.tile.BoardTileMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class TileServiceImpl {

    private final BoardTileMapper boardTileMapper;
}
