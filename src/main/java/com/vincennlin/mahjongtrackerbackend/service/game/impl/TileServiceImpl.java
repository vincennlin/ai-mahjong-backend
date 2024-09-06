package com.vincennlin.mahjongtrackerbackend.service.game.impl;

import com.vincennlin.mahjongtrackerbackend.constant.game.DefaultGameConstants;
import com.vincennlin.mahjongtrackerbackend.entity.game.GamePlayer;
import com.vincennlin.mahjongtrackerbackend.entity.game.Hand;
import com.vincennlin.mahjongtrackerbackend.entity.tile.BoardTile;
import com.vincennlin.mahjongtrackerbackend.entity.tile.PlayerTile;
import com.vincennlin.mahjongtrackerbackend.entity.tile.tilegroup.*;
import com.vincennlin.mahjongtrackerbackend.exception.InternalGameError;
import com.vincennlin.mahjongtrackerbackend.payload.tile.impl.Tile;
import com.vincennlin.mahjongtrackerbackend.repository.game.*;
import com.vincennlin.mahjongtrackerbackend.service.game.TileService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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
    public List<PlayerTile> createPlayerTileList(Hand hand) {

        GamePlayer currentPlayer = hand.getDealer();

        List<PlayerTile> playerTileList = new ArrayList<>();

        for (int i = 0; i < DefaultGameConstants.DEFAULT_PLAYER_COUNT; i++) {
            PlayerTile playerTile = new PlayerTile(hand, currentPlayer);
            playerTileList.add(playerTile);
            currentPlayer = currentPlayer.getDownwindPlayer();
        }

        for (PlayerTile playerTile : playerTileList) {
            playerTile = playerTileRepository.save(playerTile);

            HandTileGroup handTileGroup = new HandTileGroup(playerTile);
            playerTile.setHandTiles(handTileGroupRepository.save(handTileGroup));

            ExposedTileGroup exposedTileGroup = new ExposedTileGroup(playerTile);
            playerTile.setExposedTiles(exposedTileGroupRepository.save(exposedTileGroup));

            DiscardedTileGroup discardedTileGroup = new DiscardedTileGroup(playerTile);
            playerTile.setDiscardedTiles(discardedTileGroupRepository.save(discardedTileGroup));
        }

        return playerTileRepository.saveAll(playerTileList);
    }

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

        for (BoardTile boardTile : savedBoardTiles) {
            boardTile.getTileGroup().getTiles().add(boardTile);
        }

        return wallTileGroupRepository.save(wallTileGroup);
    }

    @Override
    public List<BoardTile> saveBoardTiles(List<BoardTile> boardTiles) {

        return boardTileRepository.saveAll(boardTiles);
    }

//    @Override
//    public List<PlayerTile> savePlayerTileList(List<PlayerTile> playerTileList) {
//
//        for (PlayerTile playerTile : playerTileList) {
//            playerTile = playerTileRepository.save(playerTile);
//
//            HandTileGroup handTileGroup = playerTile.getHandTiles() == null ? new HandTileGroup(playerTile) : playerTile.getHandTiles();
//            playerTile.setHandTiles(handTileGroupRepository.save(handTileGroup));
//
//            ExposedTileGroup exposedTileGroup = playerTile.getExposedTiles() == null ? new ExposedTileGroup(playerTile) : playerTile.getExposedTiles();
//            playerTile.setExposedTiles(exposedTileGroupRepository.save(exposedTileGroup));
//
//            DiscardedTileGroup discardedTileGroup = playerTile.getDiscardedTiles() == null ? new DiscardedTileGroup(playerTile) : playerTile.getDiscardedTiles();
//            playerTile.setDiscardedTiles(discardedTileGroupRepository.save(discardedTileGroup));
//        }
//
//        return playerTileRepository.saveAll(playerTileList);
//    }

    @Override
    public int getFirstTileIndex(Integer diceNumber) {

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

    @Override
    public List<PlayerTile> dealTiles(Hand hand) {

        WallTileGroup wallTileGroup = hand.getWallTileGroup();
        List<BoardTile> wallTiles = wallTileGroup.getTiles();

        List<PlayerTile> playerTileList = createPlayerTileList(hand);

        GamePlayer currentPlayer = hand.getDealer();

        for (int round = 0; round < 4; round++) {
            for (int i = 0; i < DefaultGameConstants.DEFAULT_PLAYER_COUNT; i++) {
                HandTileGroup handTileGroup = playerTileList.get(i).getHandTiles();
                for (int j = 0; j < 4; j++) {
                    BoardTile tile = wallTiles.remove(0);
                    tile.setTileGroup(handTileGroup);
                    tile.getTileGroup().getTiles().add(tile);
                }
                currentPlayer = currentPlayer.getDownwindPlayer();
            }
        }

        return playerTileRepository.saveAll(playerTileList);
    }

    @Override
    public void sortHandGroupTiles(List<PlayerTile> playerTileList) {
        for (PlayerTile playerTile : playerTileList) {
            playerTile.getHandTiles().sortHandTiles();
        }
    }

    @Override
    public void reorderWallTiles(int index, WallTileGroup wallTileGroup) {

        List<BoardTile> wallTiles = wallTileGroup.getTiles();

        List<Tile> originalTileTypes = wallTiles.stream()
                .map(BoardTile::getTile)
                .collect(Collectors.toList());

        Collections.rotate(originalTileTypes, -index);

        for (int i = 0; i < wallTiles.size(); i++) {
            wallTiles.get(i).setTile(originalTileTypes.get(i));
        }

        wallTileGroupRepository.save(wallTileGroup);
    }

    @Override
    public void initialFoulHand(PlayerTile playerTile, WallTileGroup wallTileGroup) {

        List<BoardTile> handTiles = playerTile.getHandTiles().getTiles();
        List<BoardTile> exposedTiles = playerTile.getExposedTiles().getTiles();

        while (handTiles.get(handTiles.size() - 1).getTile().isFlower()) {
            BoardTile tile = handTiles.remove(handTiles.size() - 1);
            exposedTiles.add(tile);
            tile.setTileGroup(playerTile.getExposedTiles());

            foulHand(playerTile, wallTileGroup);

            boardTileRepository.save(tile);
        }

        playerTile.getHandTiles().sortHandTiles();

        playerTileRepository.save(playerTile);
    }

    @Override
    public BoardTile drawTile(PlayerTile playerTile, WallTileGroup wallTileGroup) {

        return drawTileFromWall(playerTile, wallTileGroup, true);
    }

    @Override
    public BoardTile foulHand(PlayerTile playerTile, WallTileGroup wallTileGroup) {

        return drawTileFromWall(playerTile, wallTileGroup, false);
    }

    private BoardTile drawTileFromWall(PlayerTile playerTile, WallTileGroup wallTileGroup, boolean isFromHead) {

        List<BoardTile> wallTiles = wallTileGroup.getTiles();
        BoardTile tile = wallTiles.remove(isFromHead ? 0 : wallTiles.size() - 1);
        tile.setTileGroup(playerTile.getHandTiles());
        playerTile.getHandTiles().getTiles().add(0, tile);

        return boardTileRepository.save(tile);
    }
}
