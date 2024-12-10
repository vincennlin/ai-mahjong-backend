package com.vincennlin.mahjongtrackerbackend.service.game.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vincennlin.mahjongtrackerbackend.exception.JsonFormatException;
import com.vincennlin.mahjongtrackerbackend.payload.game.dto.PlayerViewDto;
import com.vincennlin.mahjongtrackerbackend.payload.game.request.ai.DiscardAdviceResponse;
import com.vincennlin.mahjongtrackerbackend.payload.game.request.ai.GenerateDiscardAdviceRequest;
import com.vincennlin.mahjongtrackerbackend.service.game.AiService;
import lombok.AllArgsConstructor;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class AiServiceImpl implements AiService {

    private final OpenAiChatModel openAiChatModel;

    private final ObjectMapper objectMapper;

    @Override
    public DiscardAdviceResponse generateDiscardAdvice(PlayerViewDto playerViewDto) {

        GenerateDiscardAdviceRequest request = new GenerateDiscardAdviceRequest(playerViewDto, objectMapper);

        String requestString;

        try {
            requestString = objectMapper.writeValueAsString(request);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        List<Message> messages = List.of(
                request.getInitialSystemMessage(),
                request.getResponseFormatExampleSystemMessage(),
                new UserMessage(requestString)
        );

        Prompt prompt = new Prompt(messages);

        ChatResponse response = openAiChatModel.call(prompt);

        String responseContent = response.getResults().get(0).getOutput().getContent();

        return parseResponseJson(responseContent);
    }

    private String preProcessJson(String json) {
        if (json.startsWith("```json") && json.endsWith("```")) {
            return json.substring(7, json.length() - 3).trim();
        }
        return json;
    }

    private DiscardAdviceResponse parseResponseJson(String responseContent) {
        responseContent = preProcessJson(responseContent);
        try{
            return objectMapper.readValue(responseContent, DiscardAdviceResponse.class);
        } catch (Exception e) {
            throw new JsonFormatException("Failed to parse response content to DiscardAdviceResponse", e.getMessage());
        }
    }
}
