package com.vincennlin.aimahjongbackend.payload.game.request.ai;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DiscardAdviceResponse {

    @JsonProperty(value = "discard_tile")
    private String discardTile;

    @JsonProperty(value = "discard_reason")
    private String discardReason;
}
