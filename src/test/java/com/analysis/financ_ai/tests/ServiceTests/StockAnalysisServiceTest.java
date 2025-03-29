package com.analysis.financ_ai.tests.ServiceTests;

import com.analysis.financ_ai.model.StockAnalysisResponse;
import com.analysis.financ_ai.openai.OpenAiService;
import com.analysis.financ_ai.service.StockAnalysisService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(MockitoExtension.class)
public class StockAnalysisServiceTest {

    @Mock
    private OpenAiService openAiService;

    @InjectMocks
    private StockAnalysisService stockAnalysisService;

    @Test
    public void deveRetornarRespostaComIndicadores() {
        // Arrange
        String respostaIaEsperada = "Sim, a ação bbas3 está em bom momento.";
        when(openAiService.analisarIndicadoresComIA(any()))
                .thenReturn(respostaIaEsperada);

        // Act
        StockAnalysisResponse resposta = stockAnalysisService.analisar("bbas3");

        // Assert
        assertNotNull(resposta);
        assertEquals("bbas3", resposta.getIndicadores().getTicker());
        assertEquals(respostaIaEsperada, resposta.getRespostaIa());
    }
}