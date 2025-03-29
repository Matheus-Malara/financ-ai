package com.analysis.financ_ai.controller;

import com.analysis.financ_ai.model.StockAnalysisResponse;
import com.analysis.financ_ai.service.StockAnalysisService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/analise")
public class StockAnalysisController {

    private final StockAnalysisService stockAnalysisService;

    public StockAnalysisController(StockAnalysisService stockAnalysisService) {
        this.stockAnalysisService = stockAnalysisService;
    }

    @GetMapping("/{ticker}")
    public StockAnalysisResponse analisarAcao(@PathVariable String ticker) {
        return stockAnalysisService.analisar(ticker);
    }
}