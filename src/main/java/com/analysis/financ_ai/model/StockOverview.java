package com.analysis.financ_ai.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class StockOverview {

    @JsonProperty("Symbol")
    private String symbol;

    @JsonProperty("MarketCapitalization")
    private String marketCapitalization;

    @JsonProperty("PERatio")
    private String peRatio;

    @JsonProperty("PEGRatio")
    private String pegRatio;

    @JsonProperty("PriceToBookRatio")
    private String priceToBookRatio;

    @JsonProperty("PriceToSalesRatioTTM")
    private String priceToSalesRatio;

    @JsonProperty("EVToRevenue")
    private String evToRevenue;

    @JsonProperty("EVToEBITDA")
    private String evToEbitda;

    @JsonProperty("EPS")
    private String eps;

    @JsonProperty("DividendPerShare")
    private String dividendPerShare;

    @JsonProperty("DividendYield")
    private String dividendYield;

    @JsonProperty("DividendDate")
    private String dividendDate;

    @JsonProperty("ExDividendDate")
    private String exDividendDate;

    @JsonProperty("ProfitMargin")
    private String profitMargin;

    @JsonProperty("OperatingMarginTTM")
    private String operatingMargin;

    @JsonProperty("ReturnOnAssetsTTM")
    private String returnOnAssets;

    @JsonProperty("ReturnOnEquityTTM")
    private String returnOnEquity;

    @JsonProperty("QuarterlyEarningsGrowthYOY")
    private String earningsGrowth;

    @JsonProperty("QuarterlyRevenueGrowthYOY")
    private String revenueGrowth;

    @JsonProperty("AnalystTargetPrice")
    private String analystTargetPrice;

    @JsonProperty("Beta")
    private String beta;

    @JsonProperty("52WeekHigh")
    private String maxPriceYear;

    @JsonProperty("52WeekLow")
    private String minPriceYear;

    public String toFormattedString() {
        return String.format("""
                         📊 Company: %s
                        \s
                         💰 Valuation:
                         - Market Cap: %s
                         - P/E: %s | PEG: %s | P/B: %s | P/S: %s
                         - EV/Revenue: %s | EV/EBITDA: %s
                        \s
                         📈 Indicators:
                         - EPS: %s
                         - Profit Margin: %s
                         - Operating Margin: %s
                         - ROA: %s | ROE: %s
                        \s
                         📊 Growth:
                         - Earnings Growth (YoY): %s
                         - Revenue Growth (YoY): %s
                        \s
                         📉 Volatility:
                         - Beta: %s
                         - 52-week High: %s | 52-week Low: %s
                        \s
                         💸 Dividends:
                         - Dividends per Share: %s
                         - Yield: %s
                         - Dividend Date: %s | Ex-Date: %s
                        \s
                         🎯 Analyst Target Price: %s
                        """,
                symbol,
                marketCapitalization,
                peRatio, pegRatio, priceToBookRatio, priceToSalesRatio,
                evToRevenue, evToEbitda,
                eps,
                profitMargin,
                operatingMargin,
                returnOnAssets, returnOnEquity,
                earningsGrowth,
                revenueGrowth,
                beta, maxPriceYear, minPriceYear,
                dividendPerShare, dividendYield, dividendDate, exDividendDate,
                analystTargetPrice
        );
    }
}
