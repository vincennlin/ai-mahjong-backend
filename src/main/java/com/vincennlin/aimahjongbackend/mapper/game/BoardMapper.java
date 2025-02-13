package com.vincennlin.aimahjongbackend.mapper.game;

import com.vincennlin.aimahjongbackend.entity.game.GamePlayer;
import com.vincennlin.aimahjongbackend.entity.game.Hand;
import com.vincennlin.aimahjongbackend.entity.tile.PlayerTile;
import com.vincennlin.aimahjongbackend.mapper.tile.BoardTileMapper;
import com.vincennlin.aimahjongbackend.mapper.tile.TileGroupMapper;
import com.vincennlin.aimahjongbackend.mapper.tile.TileMapper;
import com.vincennlin.aimahjongbackend.payload.game.dto.BoardDto;
import com.vincennlin.aimahjongbackend.payload.game.dto.PlayerViewDto;
import com.vincennlin.aimahjongbackend.payload.game.dto.tile.TileDto;
import com.vincennlin.aimahjongbackend.payload.game.operation.GamePlayerOperation;
import com.vincennlin.aimahjongbackend.payload.game.status.HandStatus;
import com.vincennlin.aimahjongbackend.payload.tile.impl.Tile;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BoardMapper {

    private final ModelMapper modelMapper;
    private final TileGroupMapper tileGroupMapper;
    private final BoardTileMapper boardTileMapper;
    private final TileMapper tileMapper;

    public BoardMapper() {
        this.modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        this.tileGroupMapper = new TileGroupMapper();
        this.boardTileMapper = new BoardTileMapper();
        this.tileMapper = new TileMapper();
    }

    public BoardDto mapToDto(Hand hand) {
        BoardDto boardDto = new BoardDto();
        boardDto.setGameId(hand.getGame().getId());
        boardDto.setHandId(hand.getId());
        boardDto.setActiveGamePlayerId(hand.getActiveGamePlayer().getId());
        if (hand.getLastDiscardedTile() != null) {
            boardDto.setLastDiscardedTile(boardTileMapper.mapToDto(hand.getLastDiscardedTile()));
        }
        boardDto.setStatus(hand.getStatus());
        boardDto.setAcceptableOperations(hand.getStatus().getAcceptableOperations());
        boardDto.setWallTiles(tileGroupMapper.mapWallTileGroupToDto(hand.getWallTileGroup()));
        if (hand.getPlayerTiles() != null) {
            for (PlayerTile playerTile : hand.getPlayerTiles()) {
                if (hand.getStatus() != HandStatus.FINISHED_DEALING && hand.getStatus() != HandStatus.FINISHED_BREAKING_WALL) {
                    playerTile.getHandTiles().sortHandTiles();
                }
                boardDto.getPlayerTiles().add(tileGroupMapper.mapPlayerTileToDto(playerTile));
            }
        }
        boardDto.setRoundWind(hand.getRound().getRoundWind());
        boardDto.setPrevailingWind(hand.getPrevailingWind());
        return boardDto;
    }

    public PlayerViewDto mapToPlayerViewDto(Hand hand, GamePlayer gamePlayer) {

        PlayerViewDto playerViewDto = new PlayerViewDto();

        playerViewDto.setGameId(hand.getGame().getId());
        playerViewDto.setHandId(hand.getId());
        playerViewDto.setActiveGamePlayerId(hand.getActiveGamePlayer().getId());
        playerViewDto.setSelfGamePlayerId(gamePlayer.getId());
        playerViewDto.setDownwindGamePlayerId(gamePlayer.getDownwindPlayer().getId());
        playerViewDto.setOppositeGamePlayerId(gamePlayer.getOppositePlayer().getId());
        playerViewDto.setUpwindGamePlayerId(gamePlayer.getUpwindPlayer().getId());

        if (hand.getLastDiscardedTile() != null) {
            playerViewDto.setLastDiscardedTile(boardTileMapper.mapToDto(hand.getLastDiscardedTile()));
        }

        playerViewDto.setHandStatus(hand.getStatus());
        playerViewDto.setGamePlayerStatus(gamePlayer.getStatus());

        playerViewDto.setAcceptableOperations(gamePlayer.getStatus().getAcceptableOperations(hand, gamePlayer));

        if (playerViewDto.getAcceptableOperations().contains(GamePlayerOperation.CALL_FOR_CHOW)) {
            List<List<Tile>> chowCombinations = gamePlayer.getPlayerTile().getHandTiles().getChowCombinations(hand.getLastDiscardedTile().getTile());
            List<List<TileDto>> chowCombinationsDto = new ArrayList<>();
            for (List<Tile> chowCombination : chowCombinations) {
                chowCombinationsDto.add(tileMapper.mapTilesToDtoList(chowCombination));
            }
            playerViewDto.setChowCombinations(chowCombinationsDto);
        }

        if (playerViewDto.getAcceptableOperations().contains(GamePlayerOperation.CALL_FOR_CONCEALED_KONG)) {
            List<Tile> concealedKongCombinations = gamePlayer.getPlayerTile().getHandTiles().getConcealedKongCombinations(gamePlayer.getPlayerTile().getDrawnTiles().getDrawnTile().getTile());
            playerViewDto.setConcealedKongCombinations(tileMapper.mapTilesToDtoList(concealedKongCombinations));
        }

        playerViewDto.setRoundWind(hand.getRound().getRoundWind());
        playerViewDto.setPrevailingWind(hand.getPrevailingWind());

        playerViewDto.setWallTiles(tileGroupMapper.mapWallTileGroupToDto(hand.getWallTileGroup()));

        if (hand.getPlayerTiles() != null) {
            for (PlayerTile playerTile : hand.getPlayerTiles()) {
                if (hand.getStatus() != HandStatus.FINISHED_DEALING && hand.getStatus() != HandStatus.FINISHED_BREAKING_WALL) {
                    playerTile.getHandTiles().sortHandTiles();
                }
            }
        }

        playerViewDto.setPlayerTile(tileGroupMapper.mapPlayerTileToDto(hand.getPlayerTileByGamePlayer(gamePlayer)));

        playerViewDto.setDownwindPlayerTile(tileGroupMapper.mapPlayerTileToDto(hand.getPlayerTileByGamePlayer(gamePlayer.getDownwindPlayer())));
        playerViewDto.setOppositePlayerTile(tileGroupMapper.mapPlayerTileToDto(hand.getPlayerTileByGamePlayer(gamePlayer.getDownwindPlayer().getDownwindPlayer())));
        playerViewDto.setUpwindPlayerTile(tileGroupMapper.mapPlayerTileToDto(hand.getPlayerTileByGamePlayer(gamePlayer.getDownwindPlayer().getDownwindPlayer().getDownwindPlayer())));

        playerViewDto.hideTiles();

        return playerViewDto;
    }
}
