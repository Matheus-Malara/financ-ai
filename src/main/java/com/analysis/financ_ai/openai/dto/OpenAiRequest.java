package com.analysis.financ_ai.openai.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class OpenAiRequest {
    private String model;
    private List<OpenAiMessage> messages;
}
