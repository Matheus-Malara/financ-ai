package com.analysis.financ_ai.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class StockAnalysisResponse {
    private StockIndicators indicadores;
    private String respostaIa;
}
