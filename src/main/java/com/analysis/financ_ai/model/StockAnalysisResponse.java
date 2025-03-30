package com.analysis.financ_ai.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class StockAnalysisResponse {

    @JsonProperty("indicators")
    private StockOverview indicators;

    @JsonProperty("ai_analysis")
    private AiAnalysis aiAnalysis;
}
