package com.analysis.financ_ai.service;

import com.analysis.financ_ai.model.StockAnalysisResponse;
import com.analysis.financ_ai.model.StockIndicators;
import com.analysis.financ_ai.openai.OpenAiService;
import org.springframework.stereotype.Service;

@Service
public class StockAnalysisService {

    private final OpenAiService openAiService;

    public StockAnalysisService(OpenAiService openAiService) {
        this.openAiService = openAiService;
    }

    public StockAnalysisResponse analisar(String ticker) {
        StockIndicators indicadores = new StockIndicators(
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

        String respostaIa = openAiService.analisarIndicadoresComIA(indicadores);

        return new StockAnalysisResponse(indicadores, respostaIa);
    }
}