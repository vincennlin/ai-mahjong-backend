package com.vincennlin.aimahjongbackend.service.game;

import com.vincennlin.aimahjongbackend.payload.game.dto.PlayerViewDto;
import com.vincennlin.aimahjongbackend.payload.game.request.ai.DiscardAdviceResponse;

public interface AiService {

    DiscardAdviceResponse generateDiscardAdvice(PlayerViewDto playerViewDto);
}
