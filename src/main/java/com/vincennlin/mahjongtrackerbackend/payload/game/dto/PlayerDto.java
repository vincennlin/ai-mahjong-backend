package com.vincennlin.mahjongtrackerbackend.payload.game.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.vincennlin.mahjongtrackerbackend.constant.game.playertype.PlayerType;
import com.vincennlin.mahjongtrackerbackend.payload.user.UserDto;
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

    @JsonProperty(value = "user")
    private UserDto user;

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
