package com.vincennlin.aimahjongbackend.payload.game.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.vincennlin.aimahjongbackend.payload.game.playertype.PlayerType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PlayerDto {

    @JsonProperty(value = "id")
    private Long id;

    @JsonProperty(value = "type")
    private PlayerType type;

    @JsonProperty(value = "user_name")
    private String userName;

    @JsonProperty(value = "player_name")
    private String playerName;

//    @JsonIgnore
//    public void setPlayerName(String username) {
//        if (this.type == PlayerType.HUMAN) {
//            this.playerName = username;
//        } else if (this.type == PlayerType.BOT) {
//            this.playerName = username + "_BOT";
//        } else if (this.type == PlayerType.AI) {
//            this.playerName = username + "_AI";
//        }
//    }
}
