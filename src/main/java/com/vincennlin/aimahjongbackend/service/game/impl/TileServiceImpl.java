package com.vincennlin.aimahjongbackend.service.game.impl;

import com.vincennlin.aimahjongbackend.constant.game.DefaultGameConstants;
import com.vincennlin.aimahjongbackend.entity.game.GamePlayer;
import com.vincennlin.aimahjongbackend.entity.game.Hand;
import com.vincennlin.aimahjongbackend.entity.game.Operation;
import com.vincennlin.aimahjongbackend.entity.tile.BoardTile;
import com.vincennlin.aimahjongbackend.entity.tile.PlayerTile;
import com.vincennlin.aimahjongbackend.entity.tile.tilegroup.*;
import com.vincennlin.aimahjongbackend.exception.InternalGameError;
import com.vincennlin.aimahjongbackend.exception.WebAPIException;
import com.vincennlin.aimahjongbackend.payload.board.MeldType;
import com.vincennlin.aimahjongbackend.payload.game.operation.GamePlayerOperation;
import com.vincennlin.aimahjongbackend.payload.tile.impl.Tile;
import com.vincennlin.aimahjongbackend.repository.game.*;
import com.vincennlin.aimahjongbackend.service.game.OperationService;
import com.vincennlin.aimahjongbackend.service.game.TileService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    private final FlowerTileGroupRepository flowerTileGroupRepository;
    private final ExposedTileGroupRepository exposedTileGroupRepository;
    private final DiscardedTileGroupRepository discardedTileGroupRepository;
    private final DrawnTileGroupRepository drawnTileGroupRepository;
    private final PlayerTileRepository playerTileRepository;
    private final OperationService operationService;

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

            FlowerTileGroup flowerTileGroup = new FlowerTileGroup(playerTile);
            playerTile.setFlowerTiles(flowerTileGroupRepository.save(flowerTileGroup));

            DiscardedTileGroup discardedTileGroup = new DiscardedTileGroup(playerTile);
            playerTile.setDiscardedTiles(discardedTileGroupRepository.save(discardedTileGroup));

            DrawnTileGroup drawnTileGroup = new DrawnTileGroup(playerTile);
            playerTile.setDrawnTiles(drawnTileGroupRepository.save(drawnTileGroup));
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
                FlowerTileGroup flowerTileGroup = playerTileList.get(i).getFlowerTiles();
                for (int j = 0; j < 4; j++) {
                    BoardTile boardTile = wallTiles.remove(0);
                    if (boardTile.isFlower()) {
                        flowerTileGroup.addBoardTileToTileGroup(boardTile);
                    } else {
                        handTileGroup.addBoardTileToTileGroup(boardTile);
                    }
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
    public void initialFoulHand(PlayerTile playerTile, WallTileGroup wallTileGroup, int foulHandCount, boolean isDealer) {

        HandTileGroup handTileGroup = playerTile.getHandTiles();

        boolean containsFlower = false;

        for (int i = 0; i < foulHandCount; i++) {
            BoardTile boardTile = wallTileGroup.drawTileFromWall(true);

            if (boardTile.isFlower()) {
                containsFlower = true;
            }

            if (isDealer && i == foulHandCount - 1 && !containsFlower) { // 莊家沒有後花要補
                setLastDrawnTile(playerTile, boardTile); // 將最後一張補到的設為 lastDrawnTile
            } else {
                handTileGroup.addBoardTileToTileGroup(boardTile);
                boardTileRepository.save(boardTile);
            }
        }

        playerTileRepository.save(playerTile);
    }

    @Transactional
    @Override
    public BoardTile drawTile(PlayerTile playerTile, WallTileGroup wallTileGroup) {

        return drawTileFromWall(playerTile, wallTileGroup, false);
    }

    @Transactional
    @Override
    public BoardTile foulHand(PlayerTile playerTile, WallTileGroup wallTileGroup) {

        return drawTileFromWall(playerTile, wallTileGroup, true);
    }

    private BoardTile drawTileFromWall(PlayerTile playerTile, WallTileGroup wallTileGroup, boolean isFoulHand) {

        BoardTile boardTile = wallTileGroup.drawTileFromWall(isFoulHand);
        playerTile.setLastDrawnTileToPlayerTileGroup(boardTile);

        return boardTileRepository.save(boardTile);
    }

    @Transactional
    @Override
    public BoardTile discardTile(PlayerTile playerTile, Long boardTileId, Operation operation) {

        BoardTile boardTile = boardTileRepository.findById(boardTileId)
                .orElseThrow(() -> new WebAPIException(HttpStatus.BAD_REQUEST, "Tile is not found"));

        if (!playerTile.getHandTiles().getTiles().remove(boardTile)) {
            throw new WebAPIException(HttpStatus.BAD_REQUEST, "Tile is not in player's hand");
        }

        operation.setGamePlayerOperation(GamePlayerOperation.DISCARD);
        operation.setPreviousTileGroup(boardTile.getTileGroup());
        operation.setBoardTile(boardTile);

        operationService.saveOperation(operation);

        // 捨牌後才將剛剛摸到的牌加入手牌
        DrawnTileGroup drawnTileGroup = playerTile.getDrawnTiles();
        BoardTile lastDrawnTile = playerTile.getDrawnTiles().getDrawnTile();
        drawnTileGroup.clearDrawnTile();
        playerTile.getHandTiles().addBoardTileToTileGroup(lastDrawnTile);
        boardTileRepository.save(lastDrawnTile);

        playerTile.getDiscardedTiles().addBoardTileToTileGroup(boardTile);

        return boardTileRepository.save(boardTile);
    }

    @Override
    public BoardTile setLastDrawnTile(PlayerTile playerTile, BoardTile boardTile) {

        DrawnTileGroup drawnTileGroup = playerTile.getDrawnTiles();
        drawnTileGroup.setDrawnTile(boardTile, false);

        return boardTileRepository.save(boardTile);
    }

    @Transactional
    @Override
    public void pongTile(PlayerTile playerTile, PlayerTile discardedPlayerTile, Operation operation) {

        BoardTile boardTile = getLastDiscardedTile(discardedPlayerTile);

        if (!playerTile.getHandTiles().canCallPong(discardedPlayerTile.getGamePlayer(), boardTile.getTile())) {
            throw new WebAPIException(HttpStatus.BAD_REQUEST, "Player cannot call pong");
        }

        operation.setGamePlayerOperation(GamePlayerOperation.CALL_FOR_PONG);
        operation.setBoardTile(boardTile);
        operation.setPreviousTileGroup(boardTile.getTileGroup());

        ExposedTileGroup exposedTileGroup = createExposedTileGroup(playerTile, MeldType.PONG);
        playerTile.pongTile(exposedTileGroup, boardTile);

        saveExposedTileGroup(exposedTileGroup);
    }

    @Transactional
    @Override
    public void chowTile(PlayerTile playerTile, PlayerTile discardedPlayerTile, Operation operation, int combinationIndex) {

        BoardTile boardTile = getLastDiscardedTile(discardedPlayerTile);

        if (!playerTile.getHandTiles().canCallChow(discardedPlayerTile.getGamePlayer(), boardTile.getTile())) {
            throw new WebAPIException(HttpStatus.BAD_REQUEST, "Player cannot call chow");
        }

        List<List<Tile>> chowCombinations = playerTile.getHandTiles().getChowCombinations(boardTile.getTile());

        List<Tile> chowCombination;

        if (chowCombinations.size() < 2) {
            if (combinationIndex != 0) {
                throw new WebAPIException(HttpStatus.BAD_REQUEST, "Invalid chow combination index");
            }
            chowCombination = chowCombinations.get(0);
        } else {
            if (combinationIndex < 1 || combinationIndex > chowCombinations.size()) {
                throw new WebAPIException(HttpStatus.BAD_REQUEST, "Invalid chow combination index");
            }
            chowCombination = chowCombinations.get(combinationIndex - 1);
        }

        if (chowCombination.size() != 3 || chowCombination.get(1) != boardTile.getTile()) {
            throw new WebAPIException(HttpStatus.INTERNAL_SERVER_ERROR, "Chow combination is not correct");
        }

        operation.setGamePlayerOperation(GamePlayerOperation.CALL_FOR_CHOW);
        operation.setBoardTile(boardTile);
        operation.setPreviousTileGroup(boardTile.getTileGroup());

        ExposedTileGroup exposedTileGroup = createExposedTileGroup(playerTile, MeldType.CHOW);
        playerTile.chowTile(exposedTileGroup, boardTile, chowCombination);

        saveExposedTileGroup(exposedTileGroup);
    }

    @Transactional
    @Override
    public void exposeKongTile(PlayerTile playerTile, PlayerTile discardedPlayerTile, Operation operation) {

        BoardTile boardTile = getLastDiscardedTile(discardedPlayerTile);

        if (!playerTile.getHandTiles().canCallExposedKong(discardedPlayerTile.getGamePlayer(), boardTile.getTile())) {
            throw new WebAPIException(HttpStatus.BAD_REQUEST, "Player cannot call exposed kong");
        }

        operation.setGamePlayerOperation(GamePlayerOperation.CALL_FOR_EXPOSED_KONG);
        operation.setBoardTile(boardTile);
        operation.setPreviousTileGroup(boardTile.getTileGroup());

        ExposedTileGroup exposedTileGroup = createExposedTileGroup(playerTile, MeldType.EXPOSED_KONG);
        playerTile.exposePongTile(exposedTileGroup, boardTile);

        saveExposedTileGroup(exposedTileGroup);
    }

    private ExposedTileGroup createExposedTileGroup(PlayerTile playerTile, MeldType meldType) {
        ExposedTileGroup exposedTileGroup = new ExposedTileGroup(playerTile, meldType);
        return exposedTileGroupRepository.save(exposedTileGroup);
    }

    private void saveExposedTileGroup(ExposedTileGroup exposedTileGroup) {
        exposedTileGroupRepository.save(exposedTileGroup);
    }

    private BoardTile getLastDiscardedTile(PlayerTile discardedPlayerTile) {
        BoardTile boardTile = discardedPlayerTile.getDiscardedTiles().removeLastBoardTile();
        if (boardTile == null) {
            throw new WebAPIException(HttpStatus.BAD_REQUEST, "Discarded tile is not found");
        }
        return boardTile;
    }
}
