package com.analysis.financ_ai.service;

import com.analysis.financ_ai.model.StockIndicators;
import com.analysis.financ_ai.model.AiAnalysis;
import com.analysis.financ_ai.model.StockAnalysisResponse;
import com.analysis.financ_ai.openai.OpenAiService;
import org.springframework.stereotype.Service;

@Service
public class StockAnalysisService {

    private final OpenAiService openAiService;

    public StockAnalysisService(OpenAiService openAiService) {
        this.openAiService = openAiService;
    }

    public StockAnalysisResponse analyze(String ticker) {
        StockIndicators indicators = new StockIndicators(
                ticker,
                6.5,
                0.8,
                8.0,
                15.0,
                5.2,
                120.0,
                12.3,
                14.7,
                35.0,
                6.2
        );

        String aiResponse = openAiService.analyzeIndicatorsWithAi(indicators);
        AiAnalysis aiAnalysis = parseAiTextResponse(aiResponse);

        return new StockAnalysisResponse(indicators, aiAnalysis);
    }

    private AiAnalysis parseAiTextResponse(String text) {
        String summary = "";
        String conclusion = "";

        // Quebra o texto em seções com base nos títulos
        String[] sections = text.split("(?i)(?=Summary:|Conclusion:)");

        // Itera pelas seções encontradas e identifica o título
        for (String section : sections) {
            section = section.trim();
            if (section.toLowerCase().startsWith("summary:")) {
                summary = section.substring("Summary:".length()).trim();
            } else if (section.toLowerCase().startsWith("conclusion:")) {
                conclusion = section.substring("Conclusion:".length()).trim();
            }
        }

        return new AiAnalysis(summary, conclusion);
    }

}
