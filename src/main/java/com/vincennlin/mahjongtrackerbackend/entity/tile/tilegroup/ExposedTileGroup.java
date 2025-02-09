package com.vincennlin.mahjongtrackerbackend.entity.tile.tilegroup;

import com.vincennlin.mahjongtrackerbackend.entity.tile.BoardTile;
import com.vincennlin.mahjongtrackerbackend.entity.tile.PlayerTile;
import com.vincennlin.mahjongtrackerbackend.payload.board.MeldType;
import com.vincennlin.mahjongtrackerbackend.payload.tile.impl.Tile;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "exposed_tile_groups")
public class ExposedTileGroup extends TileGroup implements PlayerTileGroup {

    public ExposedTileGroup(PlayerTile playerTile, MeldType meldType) {
        super();
        this.playerTile = playerTile;
        this.meldType = meldType;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(
            fetch = FetchType.EAGER,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH}
    )
    @JoinColumn(name = "player_tile_id", referencedColumnName = "id")
    private PlayerTile playerTile;

    @Enumerated(EnumType.STRING)
    @Column(name = "meld_type")
    private MeldType meldType;

    @OneToOne(
            fetch = FetchType.EAGER,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH}
    )
    @JoinColumn(name = "left_board_tile_id", referencedColumnName = "id")
    private BoardTile leftBoardTile;

    @OneToOne(
            fetch = FetchType.EAGER,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH}
    )
    @JoinColumn(name = "middle_board_tile_id", referencedColumnName = "id")
    private BoardTile middleBoardTile;

    @OneToOne(
            fetch = FetchType.EAGER,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH}
    )
    @JoinColumn(name = "right_board_tile_id", referencedColumnName = "id")
    private BoardTile rightBoardTile;

    @OneToOne(
            fetch = FetchType.EAGER,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH}
    )
    @JoinColumn(name = "top_board_tile_id", referencedColumnName = "id")
    private BoardTile topBoardTile;

    public void addBoardTileToTileGroup(BoardTile boardTile, char pos) {
        addBoardTileToTileGroup(boardTile);
        switch (pos) {
            case 'l':
                leftBoardTile = boardTile;
                break;
            case 'm':
                middleBoardTile = boardTile;
                break;
            case 'r':
                rightBoardTile = boardTile;
                break;
            case 't':
                topBoardTile = boardTile;
                break;
        }
    }

    public boolean isPongForTile(Tile tile) {
        return meldType == MeldType.PONG
                && getTiles().size() == 3
                && getTiles().stream().allMatch(boardTile -> boardTile.getTile().equals(tile));
    }

    @Override
    public String[] convertTilesToString() {
        StringBuilder tilesNumSb = new StringBuilder();
        StringBuilder tilesSubSb = new StringBuilder();
        tilesNumSb.append(leftBoardTile.getTile().getName().charAt(0))
                .append(middleBoardTile.getTile().getName().charAt(0))
                .append(rightBoardTile.getTile().getName().charAt(0));
        tilesSubSb.append(leftBoardTile.getTile().getName().charAt(1))
                .append(middleBoardTile.getTile().getName().charAt(1))
                .append(rightBoardTile.getTile().getName().charAt(1));
        if (topBoardTile != null) {
            tilesNumSb.append(topBoardTile.getTile().getName().charAt(0));
            tilesSubSb.append(topBoardTile.getTile().getName().charAt(1));
        }
        return new String[]{tilesNumSb.toString(), tilesSubSb.toString()};
    }
}
