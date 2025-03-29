package com.analysis.financ_ai.openai;

import com.analysis.financ_ai.model.StockIndicators;
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
    private String modelo;


    public OpenAiService(WebClient.Builder builder) {
        this.webClient = builder.baseUrl("https://api.openai.com/v1").build();
    }

    public String analisarIndicadoresComIA(StockIndicators indicadores) {
        String prompt = gerarPrompt(indicadores);

        OpenAiRequest request = new OpenAiRequest(
                modelo,
                List.of(
                        new OpenAiMessage("system", "Você é um analista de investimentos experiente."),
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
                    .map(response -> response.getChoices().get(0).getMessage().getContent())
                    .block();

        } catch (Exception e) {
            return "Erro ao chamar a API da OpenAI: " + e.getMessage();
        }
    }

    private String gerarPrompt(StockIndicators indicadores) {
        return """
                Com base nos seguintes indicadores fundamentalistas de uma empresa, diga se pode ser um bom momento para comprar essa ação. Justifique de forma breve, clara e didática:
                
                %s
                """.formatted(indicadores.toFormattedString());
    }
}