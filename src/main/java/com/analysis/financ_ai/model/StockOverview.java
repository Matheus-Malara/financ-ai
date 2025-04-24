package com.analysis.financ_ai.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class StockOverview {
    @JsonProperty("Symbol")
    String symbol;

    @JsonProperty("Name")
    String name;

    @JsonProperty("Description")
    String description;

    CompanyInfo companyInfo;

    FinancialMetrics financialMetrics;

    MarketData marketData;

    AnalystData analystData;

    DividendInfo dividendInfo;

    @Value
    @Builder
    public static class CompanyInfo {
        @JsonProperty("AssetType")
        String assetType;
        @JsonProperty("CIK")
        String cik;
        @JsonProperty("Exchange")
        String exchange;
        @JsonProperty("Currency")
        String currency;
        @JsonProperty("Country")
        String country;
        @JsonProperty("Sector")
        String sector;
        @JsonProperty("Industry")
        String industry;
        @JsonProperty("Address")
        String address;
        @JsonProperty("OfficialSite")
        String officialSite;
        @JsonProperty("FiscalYearEnd")
        String fiscalYearEnd;
        @JsonProperty("LatestQuarter")
        String latestQuarter;
    }

    @Value
    @Builder
    public static class FinancialMetrics {
        @JsonProperty("MarketCapitalization")
        String marketCapitalization;
        @JsonProperty("EBITDA")
        String ebitda;
        @JsonProperty("BookValue")
        String bookValue;
        @JsonProperty("EPS")
        String eps;
        @JsonProperty("DilutedEPSTTM")
        String dilutedEps;
        @JsonProperty("RevenuePerShareTTM")
        String revenuePerShare;
        @JsonProperty("RevenueTTM")
        String revenueTtm;
        @JsonProperty("GrossProfitTTM")
        String grossProfitTtm;
        @JsonProperty("ProfitMargin")
        String profitMargin;
        @JsonProperty("OperatingMarginTTM")
        String operatingMargin;
        @JsonProperty("ReturnOnAssetsTTM")
        String returnOnAssets;
        @JsonProperty("ReturnOnEquityTTM")
        String returnOnEquity;
        @JsonProperty("QuarterlyEarningsGrowthYOY")
        String earningsGrowth;
        @JsonProperty("QuarterlyRevenueGrowthYOY")
        String revenueGrowth;
    }

    @Value
    @Builder
    public static class MarketData {
        @JsonProperty("PERatio")
        String peRatio;
        @JsonProperty("PEGRatio")
        String pegRatio;
        @JsonProperty("PriceToBookRatio")
        String priceToBookRatio;
        @JsonProperty("PriceToSalesRatioTTM")
        String priceToSalesRatio;
        @JsonProperty("EVToRevenue")
        String evToRevenue;
        @JsonProperty("EVToEBITDA")
        String evToEbitda;
        @JsonProperty("Beta")
        String beta;
        @JsonProperty("52WeekHigh")
        String maxPriceYear;
        @JsonProperty("52WeekLow")
        String minPriceYear;
        @JsonProperty("50DayMovingAverage")
        String movingAverage50Day;
        @JsonProperty("200DayMovingAverage")
        String movingAverage200Day;
        @JsonProperty("SharesOutstanding")
        String sharesOutstanding;
    }

    @Value
    @Builder
    public static class AnalystData {
        @JsonProperty("AnalystTargetPrice")
        String analystTargetPrice;
        @JsonProperty("AnalystRatingStrongBuy")
        String analystRatingStrongBuy;
        @JsonProperty("AnalystRatingBuy")
        String analystRatingBuy;
        @JsonProperty("AnalystRatingHold")
        String analystRatingHold;
        @JsonProperty("AnalystRatingSell")
        String analystRatingSell;
        @JsonProperty("AnalystRatingStrongSell")
        String analystRatingStrongSell;
        @JsonProperty("TrailingPE")
        String trailingPe;
        @JsonProperty("ForwardPE")
        String forwardPe;
    }

    @Value
    @Builder
    public static class DividendInfo {
        @JsonProperty("DividendPerShare")
        String dividendPerShare;

        @JsonProperty("DividendYield")
        String dividendYield;

        @JsonProperty("DividendDate")
        String dividendDate;

        @JsonProperty("ExDividendDate")
        String exDividendDate;
    }

    public String toAnalysisString() {
        return String.format("""
                        📊 %s
                        %s
                        💰 %s
                        📈 %s
                        🚀 %s
                        📉 %s
                        🎯 %s
                        💸 %s
                        """,
                formatCompanyHeader(),
                formatCompanyInfo(),
                formatValuation(),
                formatPerformance(),
                formatGrowth(),
                formatRiskAndVolatility(),
                formatAnalystEstimates(),
                formatDividends()
        );
    }

    private String formatCompanyHeader() {
        return String.format("Company: %s (%s)", name, symbol);
    }

    private String formatCompanyInfo() {
        return String.format("""
                        - Sector: %s | Industry: %s
                        - Country: %s | Currency: %s""",
                companyInfo.getSector(), companyInfo.getIndustry(),
                companyInfo.getCountry(), companyInfo.getCurrency());
    }

    private String formatValuation() {
        return String.format("""
                        Valuation:
                        - Market Cap: %s
                        - P/E: %s | PEG: %s | P/B: %s | P/S: %s
                        - EV/Revenue: %s | EV/EBITDA: %s
                        - Book Value: %s | EBITDA: %s""",
                financialMetrics.getMarketCapitalization(),
                marketData.getPeRatio(), marketData.getPegRatio(),
                marketData.getPriceToBookRatio(), marketData.getPriceToSalesRatio(),
                marketData.getEvToRevenue(), marketData.getEvToEbitda(),
                financialMetrics.getBookValue(), financialMetrics.getEbitda());
    }

    private String formatPerformance() {
        return String.format("""
                        Performance:
                        - EPS: %s | Diluted EPS: %s
                        - Revenue/Share: %s | Revenue (TTM): %s
                        - Gross Profit (TTM): %s
                        - Profit Margin: %s | Operating Margin: %s
                        - ROA: %s | ROE: %s""",
                financialMetrics.getEps(), financialMetrics.getDilutedEps(),
                financialMetrics.getRevenuePerShare(), financialMetrics.getRevenueTtm(),
                financialMetrics.getGrossProfitTtm(),
                financialMetrics.getProfitMargin(), financialMetrics.getOperatingMargin(),
                financialMetrics.getReturnOnAssets(), financialMetrics.getReturnOnEquity());
    }

    private String formatGrowth() {
        return String.format("""
                        Growth:
                        - Earnings Growth YoY: %s
                        - Revenue Growth YoY: %s""",
                financialMetrics.getEarningsGrowth(),
                financialMetrics.getRevenueGrowth());
    }

    private String formatRiskAndVolatility() {
        return String.format("""
                        Risk & Volatility:
                        - Beta: %s
                        - 52w High: %s | 52w Low: %s
                        - 50d Avg: %s | 200d Avg: %s""",
                marketData.getBeta(),
                marketData.getMaxPriceYear(), marketData.getMinPriceYear(),
                marketData.getMovingAverage50Day(), marketData.getMovingAverage200Day());
    }

    private String formatAnalystEstimates() {
        return String.format("""
                        Analyst Estimates:
                        - Target Price: %s
                        - Ratings: Strong Buy: %s | Buy: %s | Hold: %s | Sell: %s | Strong Sell: %s
                        - Trailing PE: %s | Forward PE: %s""",
                analystData.getAnalystTargetPrice(),
                analystData.getAnalystRatingStrongBuy(), analystData.getAnalystRatingBuy(),
                analystData.getAnalystRatingHold(), analystData.getAnalystRatingSell(),
                analystData.getAnalystRatingStrongSell(),
                analystData.getTrailingPe(), analystData.getForwardPe());
    }

    private String formatDividends() {
        return String.format("""
                        Dividends:
                        - Div/Share: %s | Yield: %s
                        - Dividend Date: %s | Ex-Date: %s""",
                dividendInfo.getDividendPerShare(),
                dividendInfo.getDividendYield(),
                dividendInfo.getDividendDate(),
                dividendInfo.getExDividendDate());
    }


}