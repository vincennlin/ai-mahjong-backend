package com.vincennlin.mahjongtrackerbackend.payload.game.request.player;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.vincennlin.mahjongtrackerbackend.entity.user.User;
import com.vincennlin.mahjongtrackerbackend.payload.game.playertype.PlayerType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreatePlayerRequest {

    public CreatePlayerRequest(User user, PlayerType type) {
        this.user = user;
        this.type = type;
        if (type == PlayerType.HUMAN) {
            this.playerName = user.getUsername();
        }
    }

    @JsonProperty(value = "user")
    private User user;

    @JsonProperty(value = "type")
    private PlayerType type;

    @JsonProperty(value = "player_name")
    private String playerName;

//    @JsonInclude(JsonInclude.Include.NON_EMPTY)
//    @JsonProperty(value = "user_id")
//    private Long userId;
}
