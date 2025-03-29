package com.analysis.financ_ai.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class StockIndicators {

    @JsonProperty("ticker")
    private String ticker;

    @JsonProperty("priceToEarnings")
    private double priceToEarnings;

    @JsonProperty("priceToBook")
    private double priceToBook;

    @JsonProperty("dividendYield")
    private double dividendYield;

    @JsonProperty("roe")
    private double roe;

    @JsonProperty("evToEbitda")
    private double evToEbitda;

    @JsonProperty("marketCapBillion")
    private double marketCapBillion;

    @JsonProperty("netMargin")
    private double netMargin;

    @JsonProperty("roic")
    private double roic;

    @JsonProperty("debtToEquity")
    private double debtToEquity;

    @JsonProperty("revenueGrowth5y")
    private double revenueGrowth5y;

    public String toFormattedString() {
        return """
                Ticker: %s
                P/E Ratio: %.2f
                P/B Ratio: %.2f
                Dividend Yield: %.2f%%
                ROE: %.2f%%
                EV/EBITDA: %.2f
                Market Cap: $%.2fB
                Net Margin: %.2f%%
                ROIC: %.2f%%
                Debt-to-Equity: %.2f%%
                Revenue Growth (5y): %.2f%%
                """.formatted(
                ticker.toUpperCase(),
                priceToEarnings,
                priceToBook,
                dividendYield,
                roe,
                evToEbitda,
                marketCapBillion,
                netMargin,
                roic,
                debtToEquity,
                revenueGrowth5y
        );
    }
}
