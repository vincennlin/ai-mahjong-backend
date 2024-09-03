package com.vincennlin.mahjongtrackerbackend.payload.game.request;

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
public class CreateGameRequest {

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @JsonProperty(value = "base_point")
    private Integer basePoint;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @JsonProperty(value = "fann_point")
    private Integer fannPoint;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @JsonProperty(value = "dollar_per_point")
    private Integer dollarPerPoint;
}
