package com.vincennlin.mahjongtrackerbackend.payload.game.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.vincennlin.mahjongtrackerbackend.constant.game.playertype.PlayerType;
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

    @JsonProperty(value = "type")
    private PlayerType type;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @JsonProperty(value = "user_id")
    private Long userId;
}
