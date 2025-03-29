package com.analysis.financ_ai.openai.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OpenAiMessage {
    private String role;
    private String content;
}
