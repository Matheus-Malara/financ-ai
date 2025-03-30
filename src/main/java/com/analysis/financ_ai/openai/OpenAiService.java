package com.analysis.financ_ai.openai;

import com.analysis.financ_ai.model.StockOverview;
import com.analysis.financ_ai.openai.dto.OpenAiMessage;
import com.analysis.financ_ai.openai.dto.OpenAiRequest;
import com.analysis.financ_ai.openai.dto.OpenAiResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
public class OpenAiService {

    private final WebClient webClient;

    @Value("${openai.api.key}")
    private String apiKey;

    @Value("${openai.model}")
    private String model;

    public OpenAiService(WebClient.Builder builder) {
        this.webClient = builder.baseUrl("https://api.openai.com/v1").build();
    }

    public String analyzeIndicatorsWithAi(StockOverview indicators) {
        String prompt = generatePrompt(indicators);

        OpenAiRequest request = new OpenAiRequest(
                model,
                List.of(
                        new OpenAiMessage("system", "You are an experienced financial analyst."),
                        new OpenAiMessage("user", prompt)
                )
        );

        try {
            return webClient.post()
                    .uri("/chat/completions")
                    .header("Authorization", "Bearer " + apiKey)
                    .header("Content-Type", "application/json")
                    .bodyValue(request)
                    .retrieve()
                    .bodyToMono(OpenAiResponse.class)
                    .map(response -> response.getChoices().getFirst().getMessage().getContent())
                    .block();
        } catch (Exception e) {
            return "Error calling OpenAI API: " + e.getMessage();
        }
    }

    private String generatePrompt(StockOverview indicators) {
        return """
                Based on the following fundamental indicators of the company, provide an investment analysis in the following format:
                
                Summary: A 2-4 sentence summary of the company's financial health, key strengths, and risks.
                Conclusion: A final recommendation in 1 sentence, explaining if it's a good investment opportunity, considering the risks and strengths.
                
                Be concise and clear.
                
                Indicators:
                %s
                """.formatted(indicators.toFormattedString());
    }

}
