package com.analysis.financ_ai.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class StockIndicators {
    private String ticker;
    private double pl;
    private double pvp;
    private double dividendYield;
    private double roe;
    private double evEbitda;
    private double marketCap;
    private double margemLiquida;
    private double roic;
    private double dividaLiquidaPatrimonio;
    private double crescimentoReceita5Anos;

    public String toFormattedString() {
        return """
                Ticker: %s
                P/L: %.2f
                P/VP: %.2f
                Dividend Yield: %.2f%%
                ROE: %.2f%%
                EV/EBITDA: %.2f
                Market Cap: R$ %.2f bi
                Margem Líquida: %.2f%%
                ROIC: %.2f%%
                Dívida Líquida / Patrimônio: %.2f%%
                Crescimento Receita (5 anos): %.2f%%
                """.formatted(
                ticker.toUpperCase(),
                pl,
                pvp,
                dividendYield,
                roe,
                evEbitda,
                marketCap,
                margemLiquida,
                roic,
                dividaLiquidaPatrimonio,
                crescimentoReceita5Anos
        );
    }
}
