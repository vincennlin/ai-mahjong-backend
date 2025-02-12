package com.vincennlin.aimahjongbackend.payload.game.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.vincennlin.aimahjongbackend.payload.game.wind.Wind;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoundDto {

    private Wind roundWind;
}
