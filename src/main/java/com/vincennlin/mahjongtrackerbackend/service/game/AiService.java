package com.vincennlin.mahjongtrackerbackend.service.game;

import com.vincennlin.mahjongtrackerbackend.payload.game.dto.PlayerViewDto;
import com.vincennlin.mahjongtrackerbackend.payload.game.request.ai.DiscardAdviceResponse;

public interface AiService {

    DiscardAdviceResponse generateDiscardAdvice(PlayerViewDto playerViewDto);
}
