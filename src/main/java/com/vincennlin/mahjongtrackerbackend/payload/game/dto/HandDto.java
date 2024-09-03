package com.vincennlin.mahjongtrackerbackend.payload.game.dto;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.vincennlin.mahjongtrackerbackend.payload.game.wind.Wind;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class HandDto {

    @JsonProperty(value = "id")
    private Long id;

    @JsonProperty(value = "round_wind")
    private Wind roundWind;

    @JsonProperty(value = "prevailing_wind")
    private Wind prevailingWind;

    @JsonProperty(value = "dealer")
    private HandPlayerDto dealer;
}
