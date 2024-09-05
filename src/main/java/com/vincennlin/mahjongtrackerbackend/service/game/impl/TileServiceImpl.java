package com.vincennlin.mahjongtrackerbackend.service.game.impl;

import com.vincennlin.mahjongtrackerbackend.constant.game.DefaultGameConstants;
import com.vincennlin.mahjongtrackerbackend.entity.game.GamePlayer;
import com.vincennlin.mahjongtrackerbackend.entity.game.Hand;
import com.vincennlin.mahjongtrackerbackend.entity.tile.BoardTile;
import com.vincennlin.mahjongtrackerbackend.entity.tile.PlayerTile;
import com.vincennlin.mahjongtrackerbackend.entity.tile.tilegroup.*;
import com.vincennlin.mahjongtrackerbackend.exception.InternalGameError;
import com.vincennlin.mahjongtrackerbackend.payload.tile.TileComparator;
import com.vincennlin.mahjongtrackerbackend.payload.tile.impl.Tile;
import com.vincennlin.mahjongtrackerbackend.payload.tile.type.TileType;
import com.vincennlin.mahjongtrackerbackend.repository.game.*;
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

    private final BoardTileRepository boardTileRepository;
    private final WallTileGroupRepository wallTileGroupRepository;
    private final HandTileGroupRepository handTileGroupRepository;
    private final ExposedTileGroupRepository exposedTileGroupRepository;
    private final DiscardedTileGroupRepository discardedTileGroupRepository;
    private final PlayerTileRepository playerTileRepository;

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

    @Override
    public List<PlayerTile> savePlayerTileList(List<PlayerTile> playerTileList) {

        for (PlayerTile playerTile : playerTileList) {
            playerTile = playerTileRepository.save(playerTile);

            HandTileGroup handTileGroup = playerTile.getHandTiles() == null ? new HandTileGroup(playerTile) : playerTile.getHandTiles();
            playerTile.setHandTiles(handTileGroupRepository.save(handTileGroup));

            ExposedTileGroup exposedTileGroup = playerTile.getExposedTiles() == null ? new ExposedTileGroup(playerTile) : playerTile.getExposedTiles();
            playerTile.setExposedTiles(exposedTileGroupRepository.save(exposedTileGroup));

            DiscardedTileGroup discardedTileGroup = playerTile.getDiscardedTiles() == null ? new DiscardedTileGroup(playerTile) : playerTile.getDiscardedTiles();
            playerTile.setDiscardedTiles(discardedTileGroupRepository.save(discardedTileGroup));
        }

        return playerTileRepository.saveAll(playerTileList);
    }

    @Override
    public List<PlayerTile> dealTiles(Hand hand) {

        List<BoardTile> wallTiles = hand.getWallTileGroup().getTiles();
        Integer diceNumber = hand.getDiceNumber();

        GamePlayer currentPlayer = hand.getDealer();

        List<PlayerTile> playerTileList = new ArrayList<>();

        for (int i = 0; i < DefaultGameConstants.DEFAULT_PLAYER_COUNT; i++) {
            PlayerTile playerTile = new PlayerTile(hand, currentPlayer);
            playerTileList.add(playerTile);
            currentPlayer = currentPlayer.getDownwindPlayer();
        }

        playerTileList = savePlayerTileList(playerTileList);

        int currentTileIndex = getFirstTileIndex(diceNumber);

        List<BoardTile> tiles = new ArrayList<>();

        for (int round = 0; round < 4; round++) {
            for (int i = 0; i < DefaultGameConstants.DEFAULT_PLAYER_COUNT; i++) {
                HandTileGroup handTileGroup = playerTileList.get(i).getHandTiles();
                for (int j = 0; j < 4; j++) {
                    if (currentTileIndex > wallTiles.size() - 1) currentTileIndex = 0;
                    BoardTile tile = wallTiles.remove(currentTileIndex);
                    tile.setTileGroup(handTileGroup);
                    tiles.add(tile);
                }
            }
        }

        List<BoardTile> updatedTiles = saveBoardTiles(tiles);

        for (BoardTile tile : updatedTiles) {
            tile.getTileGroup().getTiles().add(tile);
        }

        return savePlayerTileList(playerTileList);
    }

    @Override
    public void sortHandGroupTiles(List<PlayerTile> playerTileList) {
        for (PlayerTile playerTile : playerTileList) {
            playerTile.getHandTiles().sortHandTiles();
        }
    }

    @Override
    public PlayerTile initialFoulHand(PlayerTile playerTile, WallTileGroup wallTileGroup) {

        List<BoardTile> handTiles = playerTile.getHandTiles().getTiles();
        List<BoardTile> exposedTiles = playerTile.getExposedTiles().getTiles();

        List<BoardTile> tiles = new ArrayList<>();

        while (handTiles.get(handTiles.size() - 1).getTile().isFlower()) {
            BoardTile tile = handTiles.remove(handTiles.size() - 1);
            exposedTiles.add(tile);
            tile.setTileGroup(playerTile.getExposedTiles());

            List<BoardTile> wallTiles = wallTileGroup.getTiles();

            BoardTile newTile = wallTiles.remove(wallTiles.size() - 1);
            newTile.setTileGroup(playerTile.getHandTiles());
            handTiles.add(0, newTile);

            tiles.add(tile);
            tiles.add(newTile);
        }

        playerTile.getHandTiles().sortHandTiles();

        saveBoardTiles(tiles);

        return playerTileRepository.save(playerTile);
    }

    private int getFirstTileIndex(Integer diceNumber) {

        int direction = diceNumber % DefaultGameConstants.DEFAULT_PLAYER_COUNT;

        int firstTileIndex = switch (direction) {
            case 1 -> 0;
            case 2 -> 108;
            case 3 -> 72;
            case 0 -> 36;
            default -> throw new InternalGameError(HttpStatus.INTERNAL_SERVER_ERROR, "Invalid dice number");
        };

        firstTileIndex += diceNumber * 2;

        return firstTileIndex;
    }
}
