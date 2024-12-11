package com.vincennlin.mahjongtrackerbackend.payload.game.request.ai;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vincennlin.mahjongtrackerbackend.payload.game.dto.PlayerViewDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.SystemMessage;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GenerateDiscardAdviceRequest {

    @JsonProperty(value = "player_tile")
    private GenerateDiscardAdviceRequestPlayerTile playerTile;

    @JsonProperty(value = "downwind_player_tile")
    private GenerateDiscardAdviceRequestPlayerTile downwindPlayerTile;

    @JsonProperty(value = "opposite_player_tile")
    private GenerateDiscardAdviceRequestPlayerTile oppositePlayerTile;

    @JsonProperty(value = "upwind_player_tile")
    private GenerateDiscardAdviceRequestPlayerTile upwindPlayerTile;

    private transient ObjectMapper objectMapper;

    public GenerateDiscardAdviceRequest(PlayerViewDto playerViewDto, ObjectMapper objectMapper) {
        this.playerTile = new GenerateDiscardAdviceRequestPlayerTile(playerViewDto.getPlayerTile());
        this.downwindPlayerTile = new GenerateDiscardAdviceRequestPlayerTile(playerViewDto.getDownwindPlayerTile());
        this.oppositePlayerTile = new GenerateDiscardAdviceRequestPlayerTile(playerViewDto.getOppositePlayerTile());
        this.upwindPlayerTile = new GenerateDiscardAdviceRequestPlayerTile(playerViewDto.getUpwindPlayerTile());
        this.objectMapper = objectMapper;
    }

    public Message getInitialSystemMessage() {
        return new SystemMessage(
                "你現在是一個精通臺灣16張麻將的麻將大師，而你的任務是根據收到的麻將牌局，決定你當下該要打哪一張牌。\n" +
                        "接下來你會收到四個玩家的手牌，其中player_tile是你的牌，downwind_player_tile是下家的牌，opposite_player_tile是對家的牌，upwind_player_tile是上家的牌。\n" +
                        "在這些牌中，hand_tiles代表玩家手牌，exposed_tiles代表玩家門前的牌，flower_tiles代表玩家的花牌，discarded_tiles代表玩家所捨出過的牌。\n" +
                        "請根據以下基本策略來選擇打牌：\n" +
                        "1. 先打出孤張字牌（東南西北中發白），因為這些牌不能組成順子，且在對手手上可能形成副露。\n" +
                        "2. 如果沒有孤張字牌，打出孤張數字牌（萬筒條），優先打出邊張（1、9）和中洞（2、8）。\n" +
                        "3. 優先捨去已經在其他玩家手上出現多次的牌，因為形成搭子的機會較小。\n" +
                        "請注意，生成的建議回應字數請介於30至50個字之間，並附上簡短的解釋。\n"
        );
    }

    public Message getResponseFormatExampleSystemMessage() {
        return new SystemMessage(
                "以下是一個範例的json格式回應：\n\n" +
                        getResponseExampleString() + "\n\n" +
                        "請注意，以上json回應僅為格式範例，實際生成的建議內容請依照收到的牌局生成。\n\n"
        );
    }

    private String getResponseExampleString() {
        DiscardAdviceResponse exampleResponse = new DiscardAdviceResponse(
                "一萬",
                "您的手牌中只有一萬為孤張，且其他玩家打出過多張二萬，一萬變為順子搭的機率偏小，因此建議您打出一萬。");
        try {
            return objectMapper.writeValueAsString(exampleResponse);
        } catch (Exception e) {
            return "Exception occurred while generating example response.";
        }
    }

}
