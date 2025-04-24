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
                You are a financial analyst. Based on the following fundamental indicators, generate an investment analysis with the following structure:
                
                Summary: Provide a concise 2–4 sentence overview of the company’s financial health. Highlight key strengths (e.g., profitability, growth potential), as well as relevant risks (e.g., valuation concerns, debt, market volatility).
                
                Conclusion: Give a one-sentence investment opinion (e.g., Buy, Hold, Avoid), clearly stating whether the stock represents a good opportunity, based on the strengths and risks mentioned.
                
                Be objective, avoid hype, and keep it clear and professional.
                
                Fundamental Indicators:
                %s
                """.formatted(indicators.toAnalysisString());
    }


}
