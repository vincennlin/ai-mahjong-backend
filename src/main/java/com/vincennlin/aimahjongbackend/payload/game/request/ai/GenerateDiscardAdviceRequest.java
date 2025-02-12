package com.vincennlin.aimahjongbackend.payload.game.request.ai;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vincennlin.aimahjongbackend.payload.game.dto.PlayerViewDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.SystemMessage;

import java.util.Arrays;
import java.util.List;

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
                        "1. 先打出孤張字牌（東南西北中發白），因為這些牌不能組成順子，且在對手手上可能形成副露。如果手中有多張孤張字牌，選擇捨去對其他玩家影響較小的牌。\n" +
                        "   所謂『孤張』，就是這張牌只有一張，且與其他任何手中的牌暫時毫無聯繫，亦無用處。\n" +
                        "2. 如果沒有孤張字牌，或是字牌都已形成對子（有相同的兩張以上），則優先打出孤張數字牌（萬筒條），也就是沒有和其他手牌有關聯的數字牌。" +
                        "3. 如果沒有孤張數牌，則捨去手牌中最不可能組成搭子的牌。" +
                        "4. 如果手牌中每張牌看似都可以組成搭子，則優先打出較邊張（1、9、2、8等）的數牌。\n" +
                        "請注意，生成的建議回應字數請介於30至50個字之間，並附上簡短的解釋。\n"
        );
    }


    public Message getResponseFormatExampleSystemMessage() {
        return new SystemMessage(
                "以下是一個範例的json格式回應：\n\n" +
                        getResponseExampleString() + "\n\n" +
                        "請注意，以上json回應僅為格式範例，實際生成的建議內容請依照收到的牌局生成。\n\n" +
                        "以下是更多的範例json格式回應：\n\n" +
                        getAdditionalResponseExamples() + "\n\n" +
                        "請注意，這些範例旨在幫助理解格式，實際生成的建議應根據收到的牌局情況來生成。"
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

    private String getAdditionalResponseExamples() {
        List<DiscardAdviceResponse> exampleResponses = Arrays.asList(
                new DiscardAdviceResponse("東風",
                        "您的手牌中只有一張東風為字牌並且為孤張，建議打出東風。"),
                new DiscardAdviceResponse("九筒",
                        "九筒為孤張數牌，且相比其他孤張數牌更為邊張，其他玩家已經打出過一張九筒，建議打出九筒以優化手牌結構。"),
                new DiscardAdviceResponse("八條",
                        "八條為唯一的孤張數張，且手中無法與其他牌形成搭子，建議打出八條。"),
                new DiscardAdviceResponse("五萬",
                        "您的手牌中雖有其他更為邊張的牌，但是只有五萬為孤張，其他牌都可以與其他牌形成搭子，建議先捨出五萬。")
        );
        try {
            return objectMapper.writeValueAsString(exampleResponses);
        } catch (Exception e) {
            return "Exception occurred while generating additional examples.";
        }
    }

}
