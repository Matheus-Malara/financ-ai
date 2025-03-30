package com.analysis.financ_ai.service;

import com.analysis.financ_ai.model.StockAnalysisResponse;
import com.analysis.financ_ai.model.StockOverview;
import com.analysis.financ_ai.openai.OpenAiService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class StockAnalysisServiceTest {


    private final StockAnalysisService stockAnalysisService;

    private final OpenAiService openAiService;

    private final WebClient mockWebClient;

    public StockAnalysisServiceTest() {
        mockWebClient = Mockito.mock(WebClient.class);
        openAiService = Mockito.mock(OpenAiService.class);
        WebClient.Builder webClientBuilder = Mockito.mock(WebClient.Builder.class);
        Mockito.when(webClientBuilder.baseUrl(any())).thenReturn(webClientBuilder);
        Mockito.when(webClientBuilder.build()).thenReturn(mockWebClient);
        stockAnalysisService = new StockAnalysisService(openAiService, webClientBuilder);
    }

    @Test
    void testAnalyze_ReturnsStockAnalysisResponse() {
        // Mock dependencies
        StockOverview mockStockOverview = new StockOverview();
        mockStockOverview.setSymbol("AAPL");

        WebClient.RequestHeadersUriSpec<?> uriSpec = Mockito.mock(WebClient.RequestHeadersUriSpec.class, Mockito.withSettings().defaultAnswer(Mockito.RETURNS_DEEP_STUBS));
        WebClient.ResponseSpec responseSpec = Mockito.mock();
        Mono<StockOverview> stockOverviewMono = Mockito.mock();

        Mockito.when(mockWebClient.get()).thenAnswer(invocation -> uriSpec);
        Mockito.when(uriSpec.uri(any(String.class))).thenAnswer(invocation -> uriSpec);
        Mockito.when(uriSpec.retrieve()).thenAnswer(res -> responseSpec);
        Mockito.when(responseSpec.bodyToMono(StockOverview.class)).thenAnswer(invocation -> stockOverviewMono);
        Mockito.when(stockOverviewMono.block()).thenReturn(mockStockOverview);


        Mockito.when(openAiService.analyzeIndicatorsWithAi(mockStockOverview))
                .thenReturn("**Summary:** AI analysis summary.\n**Conclusion:** AI analysis conclusion.");

        // Execute the method
        StockAnalysisResponse response = stockAnalysisService.analyze("AAPL");

        // Verify the result
        assertEquals("AAPL", response.getIndicators().getSymbol());
        assertTrue(response.getAiAnalysis().getSummary().contains("summary"));
        assertTrue(response.getAiAnalysis().getConclusion().contains("conclusion"));
    }

}