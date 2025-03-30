package com.analysis.financ_ai.service;

import com.analysis.financ_ai.model.StockOverview;
import com.analysis.financ_ai.model.AiAnalysis;
import com.analysis.financ_ai.model.StockAnalysisResponse;
import com.analysis.financ_ai.openai.OpenAiService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class StockAnalysisService {

    private final OpenAiService openAiService;
    private final WebClient webClient;

    @Value("${alphavantage.api.key}")
    private String apiKey;

    public StockAnalysisService(OpenAiService openAiService, WebClient.Builder webClientBuilder) {
        this.openAiService = openAiService;
        this.webClient = webClientBuilder.baseUrl("https://www.alphavantage.co").build();
    }

    public StockAnalysisResponse analyze(String ticker) {
        StockOverview indicators = getStockOverview(ticker, apiKey);
        String aiResponse = openAiService.analyzeIndicatorsWithAi(indicators);
        AiAnalysis aiAnalysis = formatAiResponse(aiResponse);

        return new StockAnalysisResponse(indicators, aiAnalysis);
    }

    public StockOverview getStockOverview(String symbol, String apiKey) {
        String url = "/query?function=OVERVIEW&symbol=" + symbol + "&apikey=" + apiKey;

        Mono<StockOverview> response = webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(StockOverview.class);

        return response.block();
    }


    public AiAnalysis formatAiResponse(String aiResponse) {
        String summary = "";
        String conclusion = "";

        boolean hasBoldMarkers = aiResponse.contains("**Summary:**") || aiResponse.contains("**Conclusion:**");

        String normalizedResponse = hasBoldMarkers
                ? aiResponse.replace("**", "")
                : aiResponse;

        String[] lines = normalizedResponse.split("\n");
        StringBuilder current = new StringBuilder();
        String currentSection = "";

        for (String line : lines) {
            String cleanedLine = line.trim();

            if (cleanedLine.toLowerCase().startsWith("summary:")) {
                if (currentSection.equals("summary")) {
                    summary = current.toString().trim();
                } else if (currentSection.equals("conclusion")) {
                    conclusion = current.toString().trim();
                }
                currentSection = "summary";
                current.setLength(0);
                current.append(cleanedLine.replaceFirst("(?i)summary:\\s*", ""));
            } else if (cleanedLine.toLowerCase().startsWith("conclusion:")) {
                if (currentSection.equals("summary")) {
                    summary = current.toString().trim();
                } else if (currentSection.equals("conclusion")) {
                    conclusion = current.toString().trim();
                }
                currentSection = "conclusion";
                current.setLength(0);
                current.append(cleanedLine.replaceFirst("(?i)conclusion:\\s*", ""));
            } else {
                current.append(" ").append(cleanedLine);
            }
        }

        if (currentSection.equals("summary")) {
            summary = current.toString().trim();
        } else if (currentSection.equals("conclusion")) {
            conclusion = current.toString().trim();
        }

        return new AiAnalysis(summary, conclusion);
    }

}
